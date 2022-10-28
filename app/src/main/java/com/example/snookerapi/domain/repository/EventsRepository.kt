package com.example.snookerapi.domain.repository

import com.example.snookerapi.domain.model.Event
import com.example.snookerapi.domain.model.EventProfile

interface EventsRepository {

    suspend fun getApiEvents(s: Int): List<Event>

    suspend fun getEventProfile(id: Int): List<EventProfile>
}