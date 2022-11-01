package com.example.snookerapi.data.di

import com.example.snookerapi.domain.usecase.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val useCaseModule = module {
    singleOf(::GetApiPlayersUseCase)
    singleOf(::GetDatabasePlayersUseCase)
    singleOf(::SaveDatabasePlayersUseCase)
    singleOf(::GetPlayerProfileUseCase)
    singleOf(::GetApiEventsUseCase)
    singleOf(::GetEventProfileUseCase)
    singleOf(::GetApiCountriesUseCase)
}