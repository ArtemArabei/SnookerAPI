package com.example.snookerapi.data.api

import com.example.snookerapi.data.model.EventDTO
import com.example.snookerapi.data.model.EventProfileDTO
import com.example.snookerapi.data.model.PlayerDTO
import com.example.snookerapi.data.model.PlayerProfileDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SnookerApi {

    @GET("?t=10&st=p")
    suspend fun getPlayers(
        @Query("s") s: Int,
    ): List<PlayerDTO>

    @GET(".")
    suspend fun getPlayerProfile(
        @Query("p") id: Int,
    ): List<PlayerProfileDTO>

    @GET("?t=5")
    suspend fun getEvents(
        @Query("s") s: Int,
    ): List<EventDTO>

    @GET(".")
    suspend fun getEventProfile(
        @Query("e") id: Int,
    ): List<EventProfileDTO>
}