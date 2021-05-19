package com.rafael.tictactoeapp.model

data class Player(val name:String, var score:Int){
    var moves: MutableList<String> = mutableListOf()
}
