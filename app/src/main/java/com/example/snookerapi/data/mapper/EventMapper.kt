package com.example.snookerapi.data.mapper

import com.example.snookerapi.data.model.EventDTO
import com.example.snookerapi.domain.model.Event

fun EventDTO.toDomain(): Event {
    return Event(
        id,
        name,
        dateOfStart,
        dateOfEnd
    )
}