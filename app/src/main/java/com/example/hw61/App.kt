package com.example.hw61

import android.app.Application
import com.example.hw61.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
            androidContext(this@App)
        }
    }
}