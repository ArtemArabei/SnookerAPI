package com.example.snookerapi.data.mapper

import com.example.snookerapi.data.model.PlayerProfileDTO
import com.example.snookerapi.domain.model.PlayerProfile

fun PlayerProfileDTO.toDomain(): PlayerProfile {
    return PlayerProfile(
        id,
        firstName,
        lastName,
        nationality,
        dateOfBorn,
        photoUrl,
        numRankingTitles,
        numMaximums
    )
}