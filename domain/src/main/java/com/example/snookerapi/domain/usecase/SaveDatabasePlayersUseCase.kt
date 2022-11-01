package com.example.snookerapi.domain.usecase

import com.example.snookerapi.domain.repository.PlayersRepository
import com.example.snookerapi.domain.model.Player

class SaveDatabasePlayersUseCase(
    private val playersRepository: PlayersRepository,
) {

    suspend operator fun invoke(users: List<Player>) {
        playersRepository.saveDatabasePlayers(users)
    }
}