package com.rafael.tictactoeapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rafael.tictactoeapp.model.Match

@Dao
interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMatch(match: Match): Long

    @Query("UPDATE match_table SET player1Score = :playerScore1, player2Score = :playerScore2, timeDate = :timeDate  WHERE id == :id")
    suspend fun updateMatch(playerScore1: Int, playerScore2: Int, timeDate: String, id: Long)

    @Query("SELECT * FROM match_table ORDER BY id ASC")
    fun readAllData(): LiveData<MutableList<Match>>

    @Query("DELETE FROM match_table WHERE id==:id")
    suspend fun deleteMatch(id: Int):Int

}