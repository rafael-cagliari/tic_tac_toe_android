package com.rafael.tictactoeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rafael.tictactoeapp.databinding.GameModeSelectionFragmentBinding


class GameModeSelectionFragment : Fragment() {

    var binding : GameModeSelectionFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = GameModeSelectionFragmentBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //selects player vs player
        binding?.buttonPvp?.setOnClickListener { findNavController().navigate(GameModeSelectionFragmentDirections.actionGameModeSelectionToPlayerNameFragment()) }

        //selects player vs. AI
        binding?.buttonCPU?.setOnClickListener { findNavController().navigate(GameModeSelectionFragmentDirections.actionGameModeSelectionToPlayerNameVsAiFragment2()) }
    }
    }
