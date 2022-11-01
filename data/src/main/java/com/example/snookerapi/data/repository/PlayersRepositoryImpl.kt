package com.example.snookerapi.data.repository

import com.example.snookerapi.data.api.SnookerApi
import com.example.snookerapi.data.mapper.toData
import com.example.snookerapi.data.mapper.toDomain
import com.example.snookerapi.domain.repository.PlayersRepository
import com.example.snookerapi.data.database.PlayerDao
import com.example.snookerapi.domain.model.Player
import com.example.snookerapi.domain.model.PlayerProfile

internal class PlayersRepositoryImpl(
    private val snookerApi: SnookerApi,
    private val playerDao: PlayerDao,
) : PlayersRepository {

    override suspend fun getApiPlayers(s: Int): List<Player> {
        return snookerApi.getPlayers(s)
            .map { it.toDomain() }
    }

    override suspend fun getDatabasePlayers(): List<Player> {
        return playerDao.getAll().map { it.toDomain() }
    }

    override suspend fun saveDatabasePlayers(players: List<Player>) {
        playerDao.insertAll(players.map { it.toData() })
    }

    override suspend fun getPlayerProfile(id: Int): List<PlayerProfile> {
        return snookerApi.getPlayerProfile(id)
            .map { it.toDomain() }
    }
}