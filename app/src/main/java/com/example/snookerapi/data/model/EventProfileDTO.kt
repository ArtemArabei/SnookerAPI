package com.example.snookerapi.data.model

import com.google.gson.annotations.SerializedName

data class EventProfileDTO(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Country")
    val country: String,
    @SerializedName("City")
    val city: String,
    @SerializedName("Venue")
    val venue: String,
    @SerializedName("StartDate")
    val dateOfStart: String,
    @SerializedName("EndDate")
    val dateOfEnd: String,
    @SerializedName("DefendingChampion")
    val defendingChampion: Int,
)