package br.com.marvel.characters.viewmodel

import androidx.lifecycle.ViewModel
import br.com.marvel.characters.repository.CharacterRepository

class CharacterListViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    var offset : Int = 0

    init {
        fecthChars()
    }

    fun getChars() = repository.getChars()

    fun fecthChars(nextPage : Boolean = false){
        if (nextPage){
            offset++
        }
        repository.fetchChars(offset)
    }

}