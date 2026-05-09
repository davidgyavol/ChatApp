package org.example.chat.app

import android.app.Application
import org.example.chat.data.di.getDataModules
import org.example.chat.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(viewModelModules)
            getDataModules()
        }
    }
}