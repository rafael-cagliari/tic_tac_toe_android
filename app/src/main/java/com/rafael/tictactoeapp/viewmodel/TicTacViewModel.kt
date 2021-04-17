package com.rafael.tictactoeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafael.tictactoeapp.model.Player


class TicTacViewModel : ViewModel() {

    private val _players = MutableLiveData<List<Player>>()
    val players:LiveData<List<Player>> get() = _players

    fun createPlayers(playerName1:String, playerName2:String){
        val player1=Player(playerName1, 0)
        val player2=Player(playerName2, 0)
        _players.value = listOf(player1, player2)
    }
}