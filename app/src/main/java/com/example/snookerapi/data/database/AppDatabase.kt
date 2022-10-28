package com.example.snookerapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snookerapi.data.model.PlayerDTO

@Database(entities = [PlayerDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao

}
