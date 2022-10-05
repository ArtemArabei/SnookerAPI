package com.example.snookerapi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Player(
    @PrimaryKey
    @SerializedName("ID")
    val id: Int,
    @ColumnInfo(name = "first_name")
    @SerializedName("FirstName")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    @SerializedName("LastName")
    val lastName: String,
    @ColumnInfo(name = "photo_url")
    @SerializedName("Photo")
    val photoUrl: String,
)