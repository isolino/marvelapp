package br.com.marvel.characters.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ThumbnailModel(
    val path: String?,
    val extension: String?
) : Parcelable