package com.example.snookerapi.domain.repository

import com.example.snookerapi.domain.model.Player
import com.example.snookerapi.domain.model.PlayerProfile

interface PlayersRepository {

    suspend fun getApiPlayers(s: Int): List<Player>

    suspend fun getDatabasePlayers(): List<Player>

    suspend fun saveDatabasePlayers(players: List<Player>)

    suspend fun getPlayerProfile(id: Int): List<PlayerProfile>
}