package com.example.snookerapi.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object SnookerService {

    private const val SNOOKER_API_URL = "http://api.snooker.org/"

    val snookerApi by lazy {
        Retrofit.Builder()
            .baseUrl(SNOOKER_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<SnookerApi>()
    }
}