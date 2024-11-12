package com.example.twossreensofkinopoisk

import android.app.Application
import com.example.twossreensofkinopoisk.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApp)
            modules(appModule)
        }
    }
}