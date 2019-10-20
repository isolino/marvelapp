package br.com.marvel

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class AppExecutors internal constructor(){
    companion object{
        val instance = AppExecutors()
    }

    val netWorkIO : ScheduledExecutorService = Executors.newScheduledThreadPool(3)
}