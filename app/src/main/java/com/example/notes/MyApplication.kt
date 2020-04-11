package com.example.notes

import android.app.Application
import com.example.notes.di.databaseModule
import com.example.notes.di.repositoryModule
import com.example.notes.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(arrayListOf(viewModelModule, repositoryModule, databaseModule))
        }
    }
}