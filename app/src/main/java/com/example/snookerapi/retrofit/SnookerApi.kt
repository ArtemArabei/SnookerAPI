package com.example.snookerapi.retrofit

import com.example.snookerapi.model.Player
import com.example.snookerapi.model.PlayerProfile
import retrofit2.Call
import retrofit2.http.*

interface SnookerApi {

   /* @Headers("X-Requested-By: ArtemMinsk")*/
    @GET("?t=10&st=p")
    //@GET("?t=10&st=p&s=2021")
    fun getPlayers(
       @Query("s") s: Int
    ): Call<List<Player>>

    @GET(".")
    fun getPlayerProfile(
        @Query("p") id: Int
    ): Call<List<PlayerProfile>>
}