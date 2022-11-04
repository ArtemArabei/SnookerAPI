package com.example.snookerapi.data.mapper

import com.example.snookerapi.data.model.events.EventProfileDTO
import com.example.snookerapi.domain.model.EventProfile

internal fun EventProfileDTO.toDomain(): EventProfile {
    return EventProfile(
        id,
        name,
        country,
        city,
        venue,
        dateOfStart,
        dateOfEnd,
        defendingChampion
    )
}