package com.example.snookerapi.data.mapper

import com.example.snookerapi.data.model.players.PlayerDTO
import com.example.snookerapi.domain.model.Player

internal fun PlayerDTO.toDomain(): Player {
    return Player(
        id,
        firstName,
        lastName,
        photoUrl
    )
}

internal fun Player.toData(): PlayerDTO {
    return PlayerDTO(
        id,
        firstName,
        lastName,
        photoUrl
    )
}