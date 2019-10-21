package br.com.marvel.characters.view

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import br.com.marvel.R
import kotlinx.android.synthetic.main.activity_base.view.*

abstract class BaseActivity : AppCompatActivity() {

    private var progressbar : ProgressBar? = null

    override fun setContentView(layoutResID: Int) {
        val constraintLayout = layoutInflater.inflate(R.layout.activity_base, null)
        val frameLayout = constraintLayout.activity_content
        layoutInflater.inflate(layoutResID, frameLayout, true)
        progressbar = constraintLayout.progress_bar
        super.setContentView(layoutResID)
    }

    fun showProgressBar(visibility : Boolean){
        progressbar?.visibility = if (visibility) VISIBLE else INVISIBLE
    }

}