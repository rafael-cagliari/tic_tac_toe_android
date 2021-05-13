package com.rafael.tictactoeapp.roomdb.repository

import androidx.lifecycle.LiveData
import com.rafael.tictactoeapp.model.Match
import com.rafael.tictactoeapp.roomdb.MatchDao

class MatchRepository(private val matchDao: MatchDao) {

    val readAllData: LiveData<List<Match>> = matchDao.readAllData()

    suspend fun addMatch(match: Match){
        matchDao.addMatch(match)
    }

}