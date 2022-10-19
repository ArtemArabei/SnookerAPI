package com.example.snookerapi.data.di

import com.example.snookerapi.data.repository.EventsRepositoryImpl
import com.example.snookerapi.data.repository.PlayersRepositoryImpl
import com.example.snookerapi.domain.repository.EventsRepository
import com.example.snookerapi.domain.repository.PlayersRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::PlayersRepositoryImpl) {
        bind<PlayersRepository>()
    }
    singleOf(::EventsRepositoryImpl) {
        bind<EventsRepository>()
    }
}