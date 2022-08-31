package com.example.snookerapi.model

import com.google.gson.annotations.SerializedName

data class Player(
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
    val fullName: String = "$firstName $lastName",
)