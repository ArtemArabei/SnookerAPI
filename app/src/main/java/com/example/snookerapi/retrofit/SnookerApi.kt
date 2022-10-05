package com.example.snookerapi.retrofit

import com.example.snookerapi.model.Player
import com.example.snookerapi.model.PlayerProfile
import retrofit2.http.GET
import retrofit2.http.Query

interface SnookerApi {

    @GET("?t=10&st=p")
    suspend fun getPlayers(
        @Query("s") s: Int,
    ): List<Player>

    @GET(".")
    suspend fun getPlayerProfile(
        @Query("p") id: Int,
    ): List<PlayerProfile>
}