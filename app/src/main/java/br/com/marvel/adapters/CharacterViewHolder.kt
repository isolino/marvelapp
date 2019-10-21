package br.com.marvel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.R
import br.com.marvel.models.CharacterModel
import br.com.marvel.util.ThumbnailUtil
import com.squareup.picasso.Picasso

class CharacterViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    private val onCharacterListener: OnCharacterListener? = null
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_character_card, parent, false)),
    View.OnClickListener {

    var image: ImageView = itemView.findViewById(R.id.image)
    var name: TextView = itemView.findViewById(R.id.name)
    var description: TextView = itemView.findViewById(R.id.description)


    fun bind(characterModel: CharacterModel) {
        name.text = characterModel.name
        description.text = characterModel.description

        Picasso.get()
            .load(ThumbnailUtil.thumbnailUrl(characterModel, ThumbnailUtil.LANDSCAPE_AMAZING))
            .into(image)
    }

    override fun onClick(v: View?) {
        onCharacterListener?.onCharacterClick(adapterPosition)
    }
}