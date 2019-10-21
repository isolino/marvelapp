package br.com.marvel.view

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.AbsListViewBindingAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.R
import br.com.marvel.adapters.CharacterListAdapter
import br.com.marvel.adapters.OnCharacterListener
import br.com.marvel.databinding.ActivityCharacterListBinding
import br.com.marvel.repository.CharacterRepository
import br.com.marvel.requests.Resource
import br.com.marvel.requests.ServiceGenerator
import br.com.marvel.viewmodel.CharacterListViewModel
import br.com.marvel.viewmodel.CharacterListViewModelFactory

class CharacterListActivity : BaseActivity(), OnCharacterListener {

    private val viewModel by lazy {
        val repo = CharacterRepository(ServiceGenerator.getMarvelApiClient())
        ViewModelProviders.of(this, CharacterListViewModelFactory(repo))
            .get(CharacterListViewModel::class.java)
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityCharacterListBinding>(this,
            R.layout.activity_character_list)
    }

    private val scrollToNextPageListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if(!recyclerView.canScrollVertically(1)){
                viewModel.fecthChars(nextPage = true)
            }
        }
    }

    private val adapter = CharacterListAdapter( this)

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
        viewModel.getChars().observe(this, Observer { resource ->
            resource?.let {
                when (it){
                    is Resource.Success -> adapter.setCharacters(it.data.orEmpty())
                    is Resource.Loading -> adapter.displayLoading()
                    is Resource.Error -> Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onCharacterClick(position: Int) {
        Toast.makeText(this, "Hero #$position has been clicked!", Toast.LENGTH_SHORT).show()
    }

}
