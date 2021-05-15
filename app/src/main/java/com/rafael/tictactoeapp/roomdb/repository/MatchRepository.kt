package com.rafael.tictactoeapp.roomdb.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.rafael.tictactoeapp.model.Match
import com.rafael.tictactoeapp.roomdb.MatchDao

class MatchRepository(private val matchDao: MatchDao) {

    val readAllData: LiveData<MutableList<Match>> = matchDao.readAllData()

    suspend fun addMatch(match: Match): Long {
       return matchDao.addMatch(match)
    }

    suspend fun updateMatch(playerScore1: Int, playerScore2: Int, timeDate:String, id: Long){
        return matchDao.updateMatch(playerScore1, playerScore2, timeDate, id)
    }

   suspend fun deleteMatch(id: Int):Int{
       return matchDao.deleteMatch(id)
   }
}