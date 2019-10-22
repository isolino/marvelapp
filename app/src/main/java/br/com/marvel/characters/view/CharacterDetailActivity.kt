package br.com.marvel.characters.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import br.com.marvel.BaseActivity
import br.com.marvel.R
import br.com.marvel.characters.models.CharacterModel
import br.com.marvel.characters.util.ThumbnailUtil
import br.com.marvel.databinding.ActivityCharacterDetailBinding
import com.squareup.picasso.Picasso

class CharacterDetailActivity : BaseActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityCharacterDetailBinding>(this,
            R.layout.activity_character_detail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hero = intent.extras?.getParcelable<CharacterModel>("hero")

        hero?.let {
            binding.idx.text = hero.id.toString()
            binding.name.text = hero.name
            binding.modified.text = hero.modified
            binding.uri.text = hero.resourceURI

            Picasso.get()
                .load(ThumbnailUtil.thumbnailUrl(hero, ThumbnailUtil.LANDSCAPE_AMAZING))
                .into(binding.image)
        }
    }
}
