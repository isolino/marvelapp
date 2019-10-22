package br.com.marvel.characters.repository

import androidx.lifecycle.LiveData
import br.com.marvel.characters.models.CharacterModel
import br.com.marvel.characters.requests.MarvelApiClient
import br.com.marvel.characters.requests.Resource

class CharacterRepository(private val marvelApiClient: MarvelApiClient) {

    fun getChars() : LiveData<Resource<List<CharacterModel>>> {
        return marvelApiClient.getChars()
    }

    fun fetchChars(offset: Int) {
        marvelApiClient.fetchChars(offset)
    }
}