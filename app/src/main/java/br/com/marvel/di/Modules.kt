package br.com.marvel.di

import br.com.marvel.repository.CharacterRepository
import br.com.marvel.requests.MarvelApiClient
import br.com.marvel.viewmodel.CharacterListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterModule = module {
    single { MarvelApiClient() }
    single { CharacterRepository(get()) }
    viewModel { CharacterListViewModel(get()) }
}