package br.com.marvel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.models.CharacterModel

class CharacterListAdapter(
    private val items: List<CharacterModel>,
    private val onCharacterListener: OnCharacterListener? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context)
        return CharacterViewHolder(v, parent, onCharacterListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CharacterViewHolder).bind(items[position])
    }

}