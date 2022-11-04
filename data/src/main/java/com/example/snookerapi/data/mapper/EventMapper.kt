package com.example.snookerapi.data.mapper

import com.example.snookerapi.data.model.events.EventDTO
import com.example.snookerapi.domain.model.Event

internal fun EventDTO.toDomain(): Event {
    return Event(
        id,
        name,
        dateOfStart,
        dateOfEnd
    )
}