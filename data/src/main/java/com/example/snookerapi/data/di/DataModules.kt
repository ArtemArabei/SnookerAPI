package com.example.snookerapi.data.di

import org.koin.dsl.module

val dataModules = module {
    includes(
        apiModule,
        databaseModule,
        repositoryModule,
        useCaseModule,
        locationModule,
    )
}