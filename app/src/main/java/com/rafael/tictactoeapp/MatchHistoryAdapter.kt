package com.rafael.tictactoeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rafael.tictactoeapp.model.Match
import org.w3c.dom.Text

class MatchHistoryAdapter:RecyclerView.Adapter<MatchHistoryAdapter.MatchViewHolder>() {

    private var matchList = emptyList<Match>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_history_item, parent, false))
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val matchItem = matchList[position]
        holder.player1Name.text=matchItem.player1Name
        holder.player2Name.text=matchItem.player2Name
        holder.player1Score.text=matchItem.player1Score.toString()
        holder.player2Score.text=matchItem.player2Score.toString()
        holder.id.text=matchItem.id.toString()
        holder.date.text= matchItem.timeDate
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    class MatchViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView = view.findViewById(R.id.id)
        val player1Name: TextView = view.findViewById(R.id.player1Name)
        val player2Name: TextView = view.findViewById(R.id.player2Name)
        val player1Score: TextView = view.findViewById(R.id.player1Score)
        val player2Score: TextView = view.findViewById(R.id.player2Score)
        val date:TextView = view.findViewById(R.id.date)
    }

    fun setData(match: List<Match>){
        this.matchList = match
        notifyDataSetChanged()
    }

}
