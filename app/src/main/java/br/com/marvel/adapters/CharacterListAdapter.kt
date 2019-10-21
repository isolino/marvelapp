package br.com.marvel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.R
import br.com.marvel.models.CharacterModel

class CharacterListAdapter(
    private var characters: List<CharacterModel>,
    private val onCharacterListener: OnCharacterListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setCharacters(characters: List<CharacterModel>){
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun getItemCount() = characters.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character_card, parent, false)
        return CharacterViewHolder(itemView, onCharacterListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CharacterViewHolder).bind(characters[position])
    }

}