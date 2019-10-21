package br.com.marvel.characters.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterModel(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val thumbnail: ThumbnailModel?
) : Parcelable{
    companion object {
        val LOADING = CharacterModel(-1, null, null, null, null, null)
        val ERROR = CharacterModel(-2, null, null, null, null, null)
    }
}
