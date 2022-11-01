package com.example.snookerapi.presentation

import android.app.Application
import com.example.snookerapi.data.di.*
import com.example.snookerapi.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SnookerDatabase : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SnookerDatabase)
            modules(
                viewModelModule,
                dataModules
            )
        }
    }
}