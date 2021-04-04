package com.example.capstoneproject

import android.app.Application
import com.example.capstoneproject.core.di.networkModule
import com.example.capstoneproject.core.di.repositoryModule
import com.example.capstoneproject.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}
