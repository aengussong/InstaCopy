package com.aengussong.instacopy.app

import android.app.Application
import com.aengussong.instacopy.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InstaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@InstaApp)
            modules(dataModule)
        }
    }
}