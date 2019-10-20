package br.com.marvel.repository

import androidx.lifecycle.LiveData
import br.com.marvel.models.CharacterModel
import br.com.marvel.requests.MarvelApiClient
import br.com.marvel.requests.ViewCallback
import br.com.marvel.util.Constants.PRIVATE_KEY
import br.com.marvel.util.Constants.PUBLIC_KEY
import br.com.marvel.util.md5

class CharacterRepository(private val marvelApiClient: MarvelApiClient) {

    fun getCharacters(cb : ViewCallback){
        val ts = System.currentTimeMillis().toString()
        val concatenation = ts + PRIVATE_KEY + PUBLIC_KEY
        val hash = concatenation.md5()

//        marvelApi.getCharacters(ts = ts, apiKey = PUBLIC_KEY, hash = hash)
//            .enqueue(NetworkCallback(cb))
    }

    fun getChars() : LiveData<List<CharacterModel>> {
        return marvelApiClient.getChars()
    }
}