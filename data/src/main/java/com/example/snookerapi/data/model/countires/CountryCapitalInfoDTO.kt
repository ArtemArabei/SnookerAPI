package com.example.snookerapi.data.model.countires

import com.google.gson.annotations.SerializedName

internal data class CountryCapitalInfoDTO(
    @SerializedName("latlng")
    val location: List<Double>?
)