package br.com.marvel.requests

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.marvel.AppExecutors
import br.com.marvel.models.CharacterModel
import br.com.marvel.requests.responses.CharactersResponse
import br.com.marvel.util.Constants.NETWORK_TIMEOUT
import br.com.marvel.util.Constants.PRIVATE_KEY
import br.com.marvel.util.Constants.PUBLIC_KEY
import br.com.marvel.util.md5
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

class MarvelApiClient {

    private val _characters = MutableLiveData<Resource<List<CharacterModel>>>()

    fun getChars() : LiveData<Resource<List<CharacterModel>>>{
        return _characters
    }

    fun fetchChars(offset: Int){

        val ts = System.currentTimeMillis().toString()
        val concatenation = ts + PRIVATE_KEY + PUBLIC_KEY
        val hash = concatenation.md5()

        _characters.postValue(Resource.Loading(_characters.value?.data.orEmpty()))

        ServiceGenerator.getMarvelApi().getCharacters(
            offset = offset, ts = ts, apiKey = PUBLIC_KEY, hash = hash
        ).enqueue(CharsCallback(_characters))

    }

    inner class CharsCallback(
        private val liveDataResult : MutableLiveData<Resource<List<CharacterModel>>>
    ) : Callback<CharactersResponse> {
        override fun onResponse(
            call: Call<CharactersResponse>,
            response: Response<CharactersResponse>
        ) {

            if (response.isSuccessful) {
                response.body()?.let {
                    if (it.data.offset == 0){
                        liveDataResult.postValue(Resource.Success(it.data.results))
                    } else {
                        val updatedList = _characters.value?.data?.plus(it.data.results) ?: it.data.results
                        _characters.postValue(Resource.Success(updatedList))
                    }
                } ?: run {
                    liveDataResult.postValue(Resource.Success(emptyList()))
                }
            } else {
                val error = response.errorBody()?.string() ?: "A error ocurred during retriving data"
                liveDataResult.postValue(Resource.Error(error))
            }

        }

        override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
            Log.e("NetworkCallback", "error during retriving data", t)
            liveDataResult.postValue(Resource.Error("Can't complete connection"))
        }
    }
}

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}