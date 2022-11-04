package com.example.snookerapi.domain.repository

import com.example.snookerapi.domain.model.Country

interface CountriesRepository {

    suspend fun getCountries(): Result<List<Country>>

}