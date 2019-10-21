package br.com.marvel.characters.requests.responses

import br.com.marvel.characters.models.CharacterModel
import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val `data`: Data,
    @SerializedName("status") val status: String
){
    data class Data(
        @SerializedName("count") val count: Int,
        @SerializedName("limit") val limit: Int,
        @SerializedName("offset") val offset: Int,
        @SerializedName("results") val results: List<CharacterModel>,
        @SerializedName("total") val total: Int
    )
}
