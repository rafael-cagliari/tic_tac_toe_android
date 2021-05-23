package com.rafael.tictactoeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rafael.tictactoeapp.databinding.PlayerNameFragmentBinding
import com.rafael.tictactoeapp.viewmodel.TicTacViewModel


class PlayerNameFragment : Fragment() {

    private lateinit var ticTacViewModel: TicTacViewModel

    var binding: PlayerNameFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding =PlayerNameFragmentBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        setHasOptionsMenu(true)
        return fragmentBinding.root
        // Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ticTacViewModel = ViewModelProvider(requireActivity()).get(TicTacViewModel::class.java)
        binding?.button?.text = getString(R.string.start_game)
        val ticTacViewModel = ViewModelProvider(requireActivity()).get(TicTacViewModel::class.java)
        //once the start button is pressed, player 1 and player 2 are created, receiving the names typed on the boxes
        binding?.button?.setOnClickListener {
            ticTacViewModel.game_mode="two players"

            //added this code so it doesnt glitch when you switch from VS computer mode
            ticTacViewModel.turn="player1"
            ticTacViewModel.first_turn="player1"
            if(binding?.player1Name?.text.toString()!= "" && binding?.player2Name?.text.toString()!=""){
            ticTacViewModel.createPlayers(binding!!.player1Name.text.toString(), binding!!.player2Name.text.toString())
        findNavController().navigate(PlayerNameFragmentDirections.actionPlayerNameFragmentToGameFragment())
    }}
}}