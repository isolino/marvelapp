package br.com.marvel.characters.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.characters.models.CharacterModel
import br.com.marvel.characters.util.ThumbnailUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_character_card.view.*

class CharacterViewHolder(
    itemView: View,
    private val onCharacterListener: OnCharacterListener
) : RecyclerView.ViewHolder(itemView) {

    fun bind(characterModel: CharacterModel) {
        itemView.name.text = characterModel.name
        itemView.description.text = characterModel.description
        itemView.idx.text = characterModel.id.toString()

        Picasso.get()
            .load(ThumbnailUtil.thumbnailUrl(characterModel, ThumbnailUtil.LANDSCAPE_AMAZING))
            .into(itemView.image)

        itemView.setOnClickListener{
            onCharacterListener.onCharacterClick(adapterPosition)
        }
    }

}