package me.henriquecocito.iddog

import android.content.Context

class Application : android.app.Application() {

    companion object App {
        private var appContext : Context? = null

        fun getContext() = appContext
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}