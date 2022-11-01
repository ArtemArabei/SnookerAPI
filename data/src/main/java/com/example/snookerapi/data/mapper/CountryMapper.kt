package com.example.snookerapi.data.mapper

import com.example.snookerapi.data.model.countires.CountryDTO
import com.example.snookerapi.domain.model.Country

internal fun CountryDTO.toDomain(): Country {
    return Country(
        name = name.common,
        capital = capital?.first(),
        latitude = capitalInfo.location?.get(0) ?: location[0],
        longitude = capitalInfo.location?.get(1) ?: location[1],
        area = area,
        population = population,
        flagUrl = flags.png,
        emblemUrl = coatOfArms.png
    )
}