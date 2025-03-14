package com.example.hw61

import android.app.Application
import com.example.hw61.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModules)
            androidContext(this@App)
        }
    }
}