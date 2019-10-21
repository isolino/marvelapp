package br.com.marvel.requests

import br.com.marvel.requests.responses.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    fun getCharacters(@Query("limit") limit: Int = 30,
                      @Query("offset") offset: Int = 0,
                      @Query("ts") ts: String,
                      @Query("apikey") apiKey: String,
                      @Query("hash") hash: String): Call<CharactersResponse>
}