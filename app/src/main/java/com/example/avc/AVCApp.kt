package com.example.avc

import android.app.Application
import com.example.avc.di.di
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AVCApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AVCApp)
            androidLogger()
            modules(di)
        }
    }
}