package br.com.marvel.characters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.R
import br.com.marvel.characters.models.CharacterModel

class CharacterListAdapter(
    private val onCharacterListener: OnCharacterListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        private const val CHARACTER_VIEW = 1
        private const val LOADING_VIEW = 2
        private const val ERROR_VIEW = 3
    }

    private var characters: List<CharacterModel> = emptyList()

    fun setCharacters(characters: List<CharacterModel>){
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun getItemCount() = characters.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            CHARACTER_VIEW -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_character_card, parent, false)
                CharacterViewHolder(itemView, onCharacterListener)
            }
            LOADING_VIEW -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_loading_list_item, parent, false)
                LoadingViewHolder(itemView)
            }
            ERROR_VIEW -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_error_list_item, parent, false)
                LoadingViewHolder(itemView)
            }
            else -> throw IllegalStateException("viewType $viewType is not correct")
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when{
            characters[position] == CharacterModel.LOADING -> LOADING_VIEW
            characters[position] == CharacterModel.ERROR -> ERROR_VIEW
            position == characters.lastIndex && position != 0 -> LOADING_VIEW
            else -> CHARACTER_VIEW
        }
    }

    private fun cleanErrorsModel(){
        val removeLoadingAndErrorPredicate : (CharacterModel) -> Boolean = {
            (it != CharacterModel.ERROR) && (it != CharacterModel.LOADING)
        }
        characters = characters.filter(removeLoadingAndErrorPredicate)
    }

    fun displayLoading(){
        cleanErrorsModel()
        if (isNotLoading()){
            characters = characters.plus(listOf(CharacterModel.LOADING))
            notifyDataSetChanged()
        }
    }

    fun displayError(){
        cleanErrorsModel()
        characters = characters.plus(listOf(CharacterModel.ERROR))
        notifyDataSetChanged()
    }

    private fun isNotLoading() : Boolean{
        return characters.isEmpty() || (characters.last() != CharacterModel.LOADING)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is CharacterViewHolder -> holder.bind(characters[position])
        }

    }

}