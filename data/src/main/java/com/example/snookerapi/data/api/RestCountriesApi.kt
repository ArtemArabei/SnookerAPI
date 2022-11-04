package com.example.snookerapi.data.api

import com.example.snookerapi.data.model.countires.CountryDTO
import retrofit2.http.GET

internal interface RestCountriesApi {

    @GET("all")
    suspend fun getCountries(): List<CountryDTO>
}