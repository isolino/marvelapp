package br.com.marvel.repository

import androidx.lifecycle.LiveData
import br.com.marvel.models.CharacterModel
import br.com.marvel.requests.MarvelApiClient
import br.com.marvel.requests.Resource

class CharacterRepository(private val marvelApiClient: MarvelApiClient) {

    fun getChars() : LiveData<Resource<List<CharacterModel>>> {
        return marvelApiClient.getChars()
    }

    fun fetchChars(offset: Int) {
        marvelApiClient.fetchChars(offset)
    }
}