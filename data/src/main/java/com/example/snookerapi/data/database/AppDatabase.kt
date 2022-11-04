package com.example.snookerapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snookerapi.data.model.players.PlayerDTO

@Database(entities = [PlayerDTO::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao

}
