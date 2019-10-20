package br.com.marvel.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterModel(
    val id: Int?,
    val name: String? = "A-Bomb (HAS)",
    val description: String? = "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! ",
    val thumbnail: ThumbnailModel? = ThumbnailModel()
) : Parcelable
