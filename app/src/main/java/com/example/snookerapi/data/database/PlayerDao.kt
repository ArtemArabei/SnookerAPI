package com.example.snookerapi.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.snookerapi.data.model.PlayerDTO

@Dao
interface PlayerDao {
    @Query("SELECT * FROM PlayerDTO")
    suspend fun getAll(): List<PlayerDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(players: List<PlayerDTO>)
}
