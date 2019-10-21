package br.com.marvel.characters.view

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.BaseActivity
import br.com.marvel.R
import br.com.marvel.characters.adapters.CharacterListAdapter
import br.com.marvel.characters.adapters.OnCharacterListener
import br.com.marvel.databinding.ActivityCharacterListBinding
import br.com.marvel.characters.requests.Resource
import br.com.marvel.characters.viewmodel.CharacterListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterListActivity : BaseActivity(), OnCharacterListener {

    private val characterViewModel : CharacterListViewModel by viewModel()
    private val adapter = CharacterListAdapter( this)

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityCharacterListBinding>(this,
            R.layout.activity_character_list)
    }

    private val scrollToNextPageListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if(!recyclerView.canScrollVertically(1)){
                characterViewModel.fecthChars(nextPage = true)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        subscribeObservers()
    }

    private fun initRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnScrollListener(scrollToNextPageListener)
    }

    private fun subscribeObservers(){
        characterViewModel.getChars().observe(this, Observer { resource ->
            resource?.let {
                when (it){
                    is Resource.Success -> adapter.setCharacters(it.data.orEmpty())
                    is Resource.Loading -> adapter.displayLoading()
                    is Resource.Error -> adapter.displayError()
                }
            }
        })
    }

    override fun onCharacterClick(position: Int) {
        Toast.makeText(this, "Hero #$position has been clicked!", Toast.LENGTH_SHORT).show()
    }

}
