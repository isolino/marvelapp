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
import java.io.IOException
import java.util.concurrent.TimeUnit

class MarvelApiClient {

    private val _characters = MutableLiveData<List<CharacterModel>>()
    private var runnable : RetrieveCharsRunnable? = null

    fun getChars() : LiveData<List<CharacterModel>>{
        return _characters
    }

    fun getCharsApi(offset: Int){
        if (runnable != null) {
            runnable = null
        }
        runnable = RetrieveCharsRunnable(offset, true)
        val handler = AppExecutors.instance.netWorkIO.submit {
            //retrive data from rest api
    //            _characters.value
        }

        AppExecutors.instance.netWorkIO.schedule({
            //let user know its timed out
            handler.cancel(true)
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
    }

    inner class RetrieveCharsRunnable(
        private val offset: Int, var cancelable: Boolean
    ) : Runnable {
        override fun run() {
            try {
                val response = getChars(offset).execute()
                if (cancelable){
                    return
                }

                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.data.offset == 0){
                            _characters.postValue(it.data.results)
                        } else {
                            val updatedList = _characters.value?.plus(it.data.results)
                            _characters.postValue(updatedList)
                        }
                    } ?: run {
                        _characters.postValue(null)
                    }
                } else {
                    response.errorBody()?.let {
                        Log.e("", it.string())
                    }
                    _characters.postValue(null)
                }
            } catch (e : IOException){
                e.printStackTrace()
                _characters.postValue(null)
            }
        }

        private fun getChars(offset : Int) : Call<CharactersResponse> {
            val ts = System.currentTimeMillis().toString()
            val concatenation = ts + PRIVATE_KEY + PUBLIC_KEY
            val hash = concatenation.md5()

            return ServiceGenerator.getMarvelApi().getCharacters(
                offset = offset, ts = ts, apiKey = PUBLIC_KEY, hash = hash
            )
        }

        private fun cancelRequest(){
            Log.d("MarvelApiClient", "canceling the search request")
            cancelable = true
        }
    }
}