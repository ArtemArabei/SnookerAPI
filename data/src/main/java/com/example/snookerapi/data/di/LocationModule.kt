package com.example.snookerapi.data.di

import com.example.snookerapi.data.service.LocationService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val locationModule = module {
    singleOf(::LocationService)
}