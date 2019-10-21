package br.com.marvel.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterModel(
    val id: Int?,
    val name: String?,
    val description: String?,
    val thumbnail: ThumbnailModel?
) : Parcelable
