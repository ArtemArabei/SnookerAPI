package com.example.snookerapi.servicelocator

import com.example.snookerapi.model.Player
import com.example.snookerapi.model.PlayerProfile
import com.example.snookerapi.retrofit.SnookerService
import kotlinx.coroutines.delay

class ApiDataSource {

    suspend fun getPlayers(): List<Player> {
        delay(1000)
        return SnookerService.snookerApi.getPlayers(SEASON_TO_SHOW)
    }

    suspend fun getPlayerProfile(id: Int): List<PlayerProfile> {
        delay(3000)
        return SnookerService.snookerApi.getPlayerProfile(id)
    }

    companion object {

        private const val SEASON_TO_SHOW = 2021

    }
}