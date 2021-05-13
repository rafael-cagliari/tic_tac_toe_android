package com.rafael.tictactoeapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rafael.tictactoeapp.model.Match

@Dao
interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMatch(match: Match)

    @Query("SELECT * FROM match_table ORDER BY id ASC")
    fun readAllData():LiveData<List<Match>>

}