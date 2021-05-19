package com.rafael.tictactoeapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "match_table")
data class Match(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val player1Name : String,
    val player1Score : Int,
    val player2Name : String,
    val player2Score : Int,
    val timeDate : String
):Parcelable