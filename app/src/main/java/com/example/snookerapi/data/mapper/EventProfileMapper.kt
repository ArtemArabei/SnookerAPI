package com.example.snookerapi.data.mapper

import com.example.snookerapi.data.model.EventProfileDTO
import com.example.snookerapi.domain.model.EventProfile

fun EventProfileDTO.toDomain(): EventProfile {
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