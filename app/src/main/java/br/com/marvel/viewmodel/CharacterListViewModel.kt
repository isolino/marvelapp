package br.com.marvel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.marvel.models.CharacterModel
import br.com.marvel.repository.CharacterRepository
import br.com.marvel.requests.ViewCallback

class CharacterListViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

//    private val _characterList = MutableLiveData<List<CharacterModel>>()
//    fun getCharacterList() : LiveData<List<CharacterModel>>{
//        return _characterList
//    }

//    init {
//        fetchCharacterList()
//    }

//    private fun fetchCharacterList(){
//
//        repository.getCharacters(object : ViewCallback {
//            override fun onSuccess(charList: List<CharacterModel>) {
//                _characterList.value = charList
//            }
//
//            override fun onError() {
//                _characterList.value = emptyList()
//            }
//        })
//    }

    fun getChars() = repository.getChars()

}