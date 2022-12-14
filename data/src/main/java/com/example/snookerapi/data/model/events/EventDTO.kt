package com.example.snookerapi.data.model.events

import com.google.gson.annotations.SerializedName

internal data class EventDTO(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("StartDate")
    val dateOfStart: String,
    @SerializedName("EndDate")
    val dateOfEnd: String,
)