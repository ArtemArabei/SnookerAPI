package com.example.snookerapi.domain.model

data class EventProfile(
    val id: Int,
    val name: String,
    val country: String,
    val city: String,
    val venue: String,
    val dateOfStart: String,
    val dateOfEnd: String,
    val defendingChampion: Int,
)