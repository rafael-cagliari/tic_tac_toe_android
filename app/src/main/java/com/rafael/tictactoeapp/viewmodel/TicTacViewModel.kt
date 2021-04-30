package com.rafael.tictactoeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafael.tictactoeapp.model.Player


class TicTacViewModel : ViewModel() {

    var turn = "player1"

    //every match is gonna start with a different player from the last, this variable guarantees that when a match is reseted, it still starts with the same player
    // player 1 is the fist player by default
    private var first_turn = "player1"


    //player 1 and 2 objects livedata
    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>> get() = _players

    fun createPlayers(playerName1: String, playerName2: String) {
        val player1 = Player(playerName1, 0)
        val player2 = Player(playerName2, 0)
        _players.value = listOf(player1, player2)
    }

    //caled by the game cells when clicked, returns an "X" or "O" depending on the turn variable
    fun setCell(): String {
        var result: String = ""
        if (turn == "player1") result = "X"
        else if (turn == "player2") result = "O"
        return result
    }

    fun switchTurn() {
        if (turn == "player1") turn = "player2"
        else if (turn == "player2") turn = "player1"
        _players.notifyObserver()
    }

    //this functions adds coordinates (the table is a 3x3 grid) of the clicked cell into the player's "moves" list property
    fun addToMovesList(cell: String) {
        if (turn == "player1") _players.value?.get(0)?.moves?.add(cell)
        else if (turn == "player2") _players.value?.get(1)?.moves?.add(cell)
    }


    //checks the coordinates added to a player's "moves" property, calling a win in case it matches one of the winning sequences
    fun checkWin() {
        var moves = _players.value!![0].moves
        if (turn == "player2") moves = _players.value!![1].moves

        //winning sequences
        val sequence1 = listOf("a1", "a2", "a3")
        val sequence2 = listOf("b1", "b2", "b3")
        val sequence3 = listOf("c1", "c2", "c3")
        val sequence4 = listOf("a1", "b1", "c1")
        val sequence5 = listOf("a3", "b2", "c1")
        val sequence6 = listOf("a3", "b3", "c3")
        val sequence7 = listOf("a2", "b2", "c2")
        val sequence8 = listOf("a1", "b2", "c3")

        val sorted = moves.sorted()

        if (sorted.containsAll(sequence1) || sorted.containsAll(sequence2) || sorted.containsAll(
                sequence3
            ) || sorted.containsAll(sequence4) || sorted.containsAll(sequence5) || sorted.containsAll(
                sequence6
            )
            || sorted.containsAll(sequence7) || sorted.containsAll(sequence8)
        ) {
            updateScore()
        } else if (_players.value!![0].moves.size + _players.value!![1].moves.size == 9) resetGame()
        else switchTurn()
    }

    private fun updateScore() {

        if (turn == "player1") _players.value!![0].score = _players.value!![0].score + 1
        else _players.value!![1].score = _players.value!![1].score + 1
        _players.notifyObserver()

        resetGame()

    }

    //updates _players, so the observer can update the UI
    private fun <T> MutableLiveData<T>.notifyObserver() {
        _players.value = _players.value
    }

    //called when a match ends, resets the grid, clears the "moves" list and guarantees the next match starts with the player that played second this match
    private fun resetGame() {
        switchFirstTurn()
        _players.value!![0].moves.clear()
        _players.value!![1].moves.clear()
        _players.notifyObserver()
    }

    //called when user presses the "reset button" on game screen, resetting the match, but still starting a new match with the same first player
    fun resetGameButton(){
        _players.value!![0].moves.clear()
        _players.value!![1].moves.clear()
        turn = first_turn
        _players.notifyObserver()
    }

    //makes every match start with a different player as first player from the last
    private fun switchFirstTurn(){
        if (first_turn == "player1") first_turn = "player2"
        else if (first_turn == "player2") first_turn = "player1"
        turn = first_turn
    }

    //resets the game and score; first player becomes player 1 by default
    fun stopGame(){
        _players.value!![0].score = 0
        _players.value!![1].score = 0
        resetGame()
        first_turn = "player1"
        turn = first_turn
        _players.notifyObserver()
    }
}
