package com.rafael.tictactoeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rafael.tictactoeapp.databinding.GameModeSelectionFragmentBinding


class GameModeSelection : Fragment() {

    val binding : GameModeSelectionFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.game_mode_selection_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.buttonPvp?.setOnClickListener { findNavController().navigate(GameModeSelectionDirections.actionGameModeSelectionToPlayerNameFragment()) }
    }
    }
