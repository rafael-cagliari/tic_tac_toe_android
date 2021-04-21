package com.rafael.tictactoeapp.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafael.tictactoeapp.GameFragment
import com.rafael.tictactoeapp.databinding.GameFragmentBinding
import com.rafael.tictactoeapp.model.Player
import kotlinx.coroutines.withContext


class TicTacViewModel : ViewModel() {

    var turn = "player1"
    var first_player = "player1"
    var reset = "OFF"

    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>> get() = _players

    fun createPlayers(playerName1: String, playerName2: String) {
        val player1 = Player(playerName1, 0)
        val player2 = Player(playerName2, 0)
        _players.value = listOf(player1, player2)
    }

    fun setCell(): String {
        var result: String = ""
        if (turn == "player1") result = "X"
        else if (turn == "player2") result = "O"
        return result
    }

    fun switchTurn() {
        if (turn == "player1") turn = "player2"
        else if (turn == "player2") turn = "player1"
    }

    fun addToMovesList(cell: String) {
        if (turn == "player1") players.value?.get(0)?.moves?.add(cell)
        else if (turn == "player2") players.value?.get(1)?.moves?.add(cell)
    }

    fun checkWin() {
        var moves = players.value!![0].moves
        if (turn == "player2") moves = players.value!![1].moves


        val sequence1 = listOf("a1", "a2", "a3")
        val sequence2 = listOf("b1", "b2", "b3")
        val sequence3 = listOf("c1", "c2", "c3")
        val sequence4 = listOf("a1", "b1", "c1")
        val sequence5 = listOf("a3", "b2", "c1")
        val sequence6 = listOf("a3", "b3", "c3")
        val sequence7 = listOf("a2", "b2", "c2")
        val sequence8 = listOf("a1", "b2", "c3")

        var sorted = moves.sorted()

        if (sorted.containsAll(sequence1) || sorted.containsAll(sequence2) || sorted.containsAll(
                sequence3
            ) || sorted.containsAll(sequence4) || sorted.containsAll(sequence5) || sorted.containsAll(
                sequence6
            )
            || sorted.containsAll(sequence7) || sorted.containsAll(sequence8)
        ) {
            Log.d("teste win", "win $moves")
            updateScore()
        }

        else if(players.value!![0].moves.size+players.value!![1].moves.size==9)resetGame()

        else switchTurn()
    }

    fun updateScore() {

        if (turn == "player1") _players.value!![0].score = _players.value!![0].score + 1
        else _players.value!![1].score = _players.value!![1].score + 1
        _players.notifyObserver()

        resetGame()

    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        _players.value = _players.value
    }

    fun resetGame(){
        switchTurn()
        _players.value!!.get(0).moves.clear()
        _players.value!!.get(1).moves.clear()
        _players.notifyObserver()


    }

}
