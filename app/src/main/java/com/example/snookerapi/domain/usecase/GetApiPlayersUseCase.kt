package com.example.snookerapi.domain.usecase

import com.example.snookerapi.domain.repository.PlayersRepository
import com.example.snookerapi.domain.model.Player

class GetApiPlayersUseCase(
    private val repository: PlayersRepository,
) {

    suspend operator fun invoke(s: Int): List<Player> =
        repository.getApiPlayers(s)
}