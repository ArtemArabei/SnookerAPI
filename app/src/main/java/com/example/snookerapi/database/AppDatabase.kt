package com.example.snookerapi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snookerapi.model.Player

@Database(entities = [Player::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao

}
