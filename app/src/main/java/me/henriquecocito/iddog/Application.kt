package me.henriquecocito.iddog

import android.content.Context
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

class Application : android.app.Application() {

    companion object App {
        private var appContext : Context? = null

        fun getContext() = appContext
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        Fabric.with(this, Crashlytics())
    }

}