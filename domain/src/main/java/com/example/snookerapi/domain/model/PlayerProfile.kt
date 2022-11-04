package com.example.snookerapi.domain.model

data class PlayerProfile(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val nationality: String,
    val dateOfBorn: String,
    val photoUrl: String,
    val numRankingTitles: Int,
    val numMaximums: Int,
)
