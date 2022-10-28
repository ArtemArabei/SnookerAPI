package com.example.snookerapi.presentation

import android.app.Application
import com.example.snookerapi.data.di.apiModule
import com.example.snookerapi.data.di.repositoryModule
import com.example.snookerapi.data.di.useCaseModule
import com.example.snookerapi.data.di.databaseModule
import com.example.snookerapi.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SnookerDatabase : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SnookerDatabase)
            modules(
                apiModule,
                databaseModule,
                viewModelModule,
                repositoryModule,
                useCaseModule,
            )
        }
    }
}