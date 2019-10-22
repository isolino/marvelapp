package br.com.marvel.characters.util

import br.com.marvel.characters.models.CharacterModel

class ThumbnailUtil {
    companion object{
        const val LANDSCAPE_SMALL = "landscape_small"
        const val LANDSCAPE_MEDIUM = "landscape_medium"
        const val LANDSCAPE_XLARGE = "landscape_xlarge"
        const val LANDSCAPE_AMAZING = "landscape_amazing"
        const val LANDSCAPE_INCREDIBLE = "landscape_incredible"

        fun thumbnailUrl(characterModel: CharacterModel, variant: String) : String {
            val path = characterModel.thumbnail?.path
            val ext = characterModel.thumbnail?.extension
            return "$path/$variant.$ext"
        }
    }
}