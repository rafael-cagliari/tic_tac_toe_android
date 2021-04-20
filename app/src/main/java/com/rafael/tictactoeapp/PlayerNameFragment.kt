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


    var binding: PlayerNameFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding =PlayerNameFragmentBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
        // Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.button?.text = "Start Game"
        val ticTacViewModel = ViewModelProvider(requireActivity()).get(TicTacViewModel::class.java)
        //once the start button is pressed, player 1 and player 2 are created, receiving the names typed on the boxes
        binding?.button?.setOnClickListener { ticTacViewModel.createPlayers(binding!!.player1Name.text.toString(), binding!!.player2Name.text.toString())
        findNavController().navigate(PlayerNameFragmentDirections.actionPlayerNameFragmentToGameFragment())
    }
}}