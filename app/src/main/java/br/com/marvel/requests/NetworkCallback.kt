package br.com.marvel.requests

import br.com.marvel.models.CharacterModel
import br.com.marvel.requests.responses.CharactersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ViewCallback {
    fun onSuccess(charList: List<CharacterModel>)
    fun onError()
}

class NetworkCallback(private val viewCallBack : ViewCallback) : Callback<CharactersResponse> {
    override fun onResponse(
        call: Call<CharactersResponse>,
        response: Response<CharactersResponse>
    ) {

        if (response.isSuccessful) {
            response.body()?.let {
                viewCallBack.onSuccess(it.data.results)
            } ?: run {
                viewCallBack.onError()
            }
        } else {
            viewCallBack.onError()
        }

    }

    override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
        viewCallBack.onError()
    }
}