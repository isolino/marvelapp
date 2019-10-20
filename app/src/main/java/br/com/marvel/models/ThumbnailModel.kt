package br.com.marvel.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ThumbnailModel(
    val path: String? = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16",
    val extension: String? = "jpg"
) : Parcelable