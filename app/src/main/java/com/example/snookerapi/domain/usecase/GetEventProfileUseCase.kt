package com.example.snookerapi.domain.usecase

import com.example.snookerapi.domain.model.EventProfile
import com.example.snookerapi.domain.repository.EventsRepository

class GetEventProfileUseCase(
    private val repository: EventsRepository,
) {

    suspend operator fun invoke(id: Int): List<EventProfile> =
        repository.getEventProfile(id)
}