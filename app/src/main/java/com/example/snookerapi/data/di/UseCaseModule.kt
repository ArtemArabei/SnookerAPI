package com.example.snookerapi.data.di

import com.example.snookerapi.domain.usecase.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::GetApiPlayersUseCase)
    singleOf(::GetDatabasePlayersUseCase)
    singleOf(::SaveDatabasePlayersUseCase)
    singleOf(::GetPlayerProfileUseCase)
    singleOf(::GetEventProfileUseCase)
    singleOf(::GetApiEventsUseCase)
}