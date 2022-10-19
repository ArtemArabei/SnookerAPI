package com.example.snookerapi.domain.usecase

import com.example.snookerapi.domain.model.Event
import com.example.snookerapi.domain.repository.EventsRepository

class GetApiEventsUseCase(
    private val repository: EventsRepository,
) {

    suspend operator fun invoke(s: Int): List<Event> =
        repository.getApiEvents(s)
}