package org.example.chat

import android.app.Application
import org.example.chat.di.initKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        initKoin {

        }
    }
}
