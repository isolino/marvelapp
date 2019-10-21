package br.com.marvel.adapters

import android.content.Context
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

class CharacterListAdapter(
    private val ctx: Context,
    private val items: List<CharacterModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mOnItemClickListener: OnItemClickListener? = null

    override fun getItemCount() = items.size

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: CharacterModel, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mItemClickListener
    }

    inner class CharacterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_character_card, parent, false)) {
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh: RecyclerView.ViewHolder
        val v = LayoutInflater.from(parent.context)
        vh = CharacterViewHolder(v, parent)
        return vh
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CharacterViewHolder) {
            holder.bind(items[position])
        }
    }

}