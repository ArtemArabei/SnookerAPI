package com.example.snookerapi.data.repository

import com.example.snookerapi.data.api.RestCountriesApi
import com.example.snookerapi.data.mapper.toDomain
import com.example.snookerapi.domain.repository.CountriesRepository

internal class CountriesRepositoryImpl(
    private val restCountriesApi: RestCountriesApi
) : CountriesRepository {

    override suspend fun getCountries() = runCatching {
        restCountriesApi.getCountries()
    }.map { countries ->
        countries.map { it.toDomain() }
    }
}