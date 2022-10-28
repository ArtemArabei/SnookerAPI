package com.example.snookerapi.data.mapper

import com.example.snookerapi.data.model.PlayerDTO
import com.example.snookerapi.domain.model.Player

fun PlayerDTO.toDomain(): Player {
    return Player(
        id,
        firstName,
        lastName,
        photoUrl
    )
}

fun Player.toData(): PlayerDTO {
    return PlayerDTO(
        id,
        firstName,
        lastName,
        photoUrl
    )
}