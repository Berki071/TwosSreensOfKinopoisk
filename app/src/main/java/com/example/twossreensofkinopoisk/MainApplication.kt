package com.example.twossreensofkinopoisk

import android.app.Application
import org.koin.core.context.GlobalContext.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

//        startKoin {
//            modules(appModule)
//        }
    }
}