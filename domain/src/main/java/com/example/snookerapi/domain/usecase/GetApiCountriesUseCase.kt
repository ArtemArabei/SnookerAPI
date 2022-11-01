package com.example.snookerapi.domain.usecase

import com.example.snookerapi.domain.model.Country
import com.example.snookerapi.domain.repository.CountriesRepository

class GetApiCountriesUseCase(
    private val repository: CountriesRepository,
) {

    suspend operator fun invoke(): Result<List<Country>> =
        repository.getCountries()
}