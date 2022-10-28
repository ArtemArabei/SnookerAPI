package com.example.snookerapi.domain.usecase

import com.example.snookerapi.domain.repository.PlayersRepository
import com.example.snookerapi.domain.model.Player

class GetDatabasePlayersUseCase(
    private val playersRepository: PlayersRepository,
) {

    suspend operator fun invoke(): List<Player> {
        return playersRepository.getDatabasePlayers()
    }
}