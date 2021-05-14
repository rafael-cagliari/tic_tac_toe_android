package com.rafael.tictactoeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rafael.tictactoeapp.model.Match

class MatchHistoryAdapter(val matchList: List<Match>):RecyclerView.Adapter<MatchHistoryAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MatchViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_history_item, parent, false)
        return MatchViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MatchHistoryAdapter.MatchViewHolder, position: Int) {
        val matchItem = matchList[position]
        holder.Player1Name.text = matchItem.player1Name
        holder.Player1Score.text=matchItem.player2Name
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    class MatchViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val Player1Name: TextView = view.findViewById(R.id.player1Name)
        val Player1Score: TextView = view.findViewById(R.id.player1Score)

    }
}
