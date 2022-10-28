package com.example.snookerapi.data.repository

import com.example.snookerapi.data.api.SnookerApi
import com.example.snookerapi.data.mapper.toDomain
import com.example.snookerapi.domain.model.Event
import com.example.snookerapi.domain.model.EventProfile
import com.example.snookerapi.domain.repository.EventsRepository

class EventsRepositoryImpl(
    private val snookerApi: SnookerApi,
) : EventsRepository {

    override suspend fun getApiEvents(s: Int): List<Event> {
        return snookerApi.getEvents(s)
            .map { it.toDomain() }
    }

    override suspend fun getEventProfile(id: Int): List<EventProfile> {
        return snookerApi.getEventProfile(id)
            .map { it.toDomain() }
    }
}