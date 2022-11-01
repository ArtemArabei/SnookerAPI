package com.example.snookerapi.data.di

import com.example.snookerapi.data.repository.CountriesRepositoryImpl
import com.example.snookerapi.data.repository.EventsRepositoryImpl
import com.example.snookerapi.data.repository.PlayersRepositoryImpl
import com.example.snookerapi.domain.repository.CountriesRepository
import com.example.snookerapi.domain.repository.EventsRepository
import com.example.snookerapi.domain.repository.PlayersRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val repositoryModule = module {
    singleOf(::PlayersRepositoryImpl) {
        bind<PlayersRepository>()
    }
    singleOf(::EventsRepositoryImpl) {
        bind<EventsRepository>()
    }
    singleOf(::CountriesRepositoryImpl) {
        bind<CountriesRepository>()
    }
}