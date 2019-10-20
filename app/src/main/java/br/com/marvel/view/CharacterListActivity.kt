package br.com.marvel.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.marvel.R
import br.com.marvel.databinding.ActivityCharacterListBinding
import br.com.marvel.repository.CharacterRepository
import br.com.marvel.requests.ServiceGenerator
import br.com.marvel.viewmodel.CharacterListViewModel
import br.com.marvel.viewmodel.CharacterListViewModelFactory

class CharacterListActivity : BaseActivity() {

    private val viewModel by lazy {
        val repo = CharacterRepository(ServiceGenerator.getMarvelApiClient())
        ViewModelProviders.of(this, CharacterListViewModelFactory(repo))
            .get(CharacterListViewModel::class.java)
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityCharacterListBinding>(this,
            R.layout.activity_character_list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.getChars().observe(this, Observer { chars ->
            val adapter = CharacterListAdapter(this, chars)
            binding.recyclerView.adapter = adapter
        })
    }

}
