package com.example.snookerapi.domain.usecase

import com.example.snookerapi.domain.model.PlayerProfile
import com.example.snookerapi.domain.repository.PlayersRepository

class GetPlayerProfileUseCase(
    private val repository: PlayersRepository,
) {

    suspend operator fun invoke(id: Int): List<PlayerProfile> =
        repository.getPlayerProfile(id)
}