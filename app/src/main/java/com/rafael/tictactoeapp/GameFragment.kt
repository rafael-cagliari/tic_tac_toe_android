package com.rafael.tictactoeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rafael.tictactoeapp.databinding.GameFragmentBinding
import com.rafael.tictactoeapp.viewmodel.TicTacViewModel


class GameFragment : Fragment() {

    private lateinit var ticTacViewModel: TicTacViewModel

    var binding:GameFragmentBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = GameFragmentBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        ticTacViewModel = ViewModelProvider(requireActivity()).get(TicTacViewModel::class.java)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ticTacViewModel.players.observe(viewLifecycleOwner, Observer { binding!!.apply {
            scoreBoardPlayer1.text=ticTacViewModel.players.value?.get(0)?.name
            scoreBoardScores.text=" ${ticTacViewModel.players.value?.get(0)?.score}  X  ${ticTacViewModel.players.value?.get(1)?.score} "
            scoreBoardPlayer2.text=ticTacViewModel.players.value?.get(1)?.name}})
    }
}
