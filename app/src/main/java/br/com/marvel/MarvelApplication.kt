package br.com.marvel

import android.app.Application
import br.com.marvel.di.characterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    private val appModules = listOf(characterModule)

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MarvelApplication)
            modules(appModules)
        }
    }
}