package com.example.snookerapi.data.di

import androidx.room.Room
import com.example.snookerapi.data.database.AppDatabase
import org.koin.dsl.module

internal val databaseModule = module {
    single {
        Room
            .databaseBuilder(
                get(),
                AppDatabase::class.java,
                "database-room"
            )
            .build()
    }
    single { get<AppDatabase>().playerDao() }
}