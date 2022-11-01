package com.example.snookerapi.data.model.players

import com.google.gson.annotations.SerializedName

internal data class PlayerProfileDTO(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("FirstName")
    val firstName: String,
    @SerializedName("LastName")
    val lastName: String,
    @SerializedName("Nationality")
    val nationality: String,
    @SerializedName("Born")
    val dateOfBorn: String,
    @SerializedName("Photo")
    val photoUrl: String,
    @SerializedName("NumRankingTitles")
    val numRankingTitles: Int,
    @SerializedName("NumMaximums")
    val numMaximums: Int,
)