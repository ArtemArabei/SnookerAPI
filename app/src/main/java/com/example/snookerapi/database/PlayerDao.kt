package com.example.snookerapi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.snookerapi.model.Player

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player")
    suspend fun getAll(): List<Player>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(players: List<Player>)
}
