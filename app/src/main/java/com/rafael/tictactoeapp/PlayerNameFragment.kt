package com.rafael.tictactoeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rafael.tictactoeapp.databinding.PlayerNameFragmentBinding
import com.rafael.tictactoeapp.viewmodel.TicTacViewModel


class PlayerNameFragment : Fragment() {

    private val ticTacViewModel: TicTacViewModel by viewModels()

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

        ticTacViewModel.players.observe(viewLifecycleOwner, Observer { playerState -> binding!!.text1.text=
            ticTacViewModel.players.value?.get(0)?.name ?: ""
        })


        binding?.button?.setOnClickListener { ticTacViewModel.createPlayers(binding!!.playerName.text.toString(), binding!!.playerName.text.toString())

    }
}}