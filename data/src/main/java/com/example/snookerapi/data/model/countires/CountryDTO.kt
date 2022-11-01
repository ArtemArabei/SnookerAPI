package com.example.snookerapi.data.model.countires

import com.google.gson.annotations.SerializedName

internal data class CountryDTO(
    val name: CountryNameDTO,
    val capital: List<String>?,
    @SerializedName("latlng")
    val location: List<Double>,
    val area: Double,
    val population: Long,
    val flags: CountryFlagDTO,
    val coatOfArms: CountryEmblemDTO,
    val capitalInfo: CountryCapitalInfoDTO
)