package com.example.snookerapi.domain.model

data class Country(
    val name: String,
    val capital: String?,
    val latitude: Double,
    val longitude: Double,
    val area: Double,
    val population: Long,
    val flagUrl: String,
    val emblemUrl: String?
)