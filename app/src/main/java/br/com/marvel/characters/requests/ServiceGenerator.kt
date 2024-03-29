package br.com.marvel.characters.requests

import br.com.marvel.common.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    companion object{
        private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        private val retrofit = retrofitBuilder.build()

        private val marvelApi = retrofit.create(MarvelApi::class.java)

        fun getMarvelApi() : MarvelApi = marvelApi

        fun getMarvelApiClient() = MarvelApiClient()
    }

}