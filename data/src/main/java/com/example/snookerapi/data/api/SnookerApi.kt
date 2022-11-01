package com.example.snookerapi.data.api

import com.example.snookerapi.data.model.events.EventDTO
import com.example.snookerapi.data.model.events.EventProfileDTO
import com.example.snookerapi.data.model.players.PlayerDTO
import com.example.snookerapi.data.model.players.PlayerProfileDTO
import retrofit2.http.GET
import retrofit2.http.Query

internal interface SnookerApi {

    @GET("?t=$REQUEST_ID_ALL_PLAYERS&st=p")
    suspend fun getPlayers(
        @Query("s") s: Int,
    ): List<PlayerDTO>

    @GET(".")
    suspend fun getPlayerProfile(
        @Query("p") id: Int,
    ): List<PlayerProfileDTO>

    @GET("?t=$REQUEST_ID_ALL_EVENTS")
    suspend fun getEvents(
        @Query("s") s: Int,
    ): List<EventDTO>

    @GET(".")
    suspend fun getEventProfile(
        @Query("e") id: Int,
    ): List<EventProfileDTO>

    companion object {

        private const val REQUEST_ID_ALL_PLAYERS = 10
        private const val REQUEST_ID_ALL_EVENTS = 5

    }
}