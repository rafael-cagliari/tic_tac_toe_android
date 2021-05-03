package com.rafael.tictactoeapp

import android.annotation.SuppressLint
import android.media.MediaPlayer
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

    var binding: GameFragmentBinding? = null
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ViewModel observer
        ticTacViewModel.players.observe(viewLifecycleOwner, Observer {
            binding!!.apply {

                //make the player's name change color during their turn
                if(ticTacViewModel.turn=="player1") {scoreBoardPlayer1.setTextColor(resources.getColor(R.color.purple_700)); scoreBoardPlayer2.setTextColor(resources.getColor(R.color.black)) }
                if(ticTacViewModel.turn=="player2") {scoreBoardPlayer2.setTextColor(resources.getColor(R.color.purple_700)); scoreBoardPlayer1.setTextColor(resources.getColor(R.color.black)) }

                //shows names and score; updates score
                scoreBoardPlayer1.text = ticTacViewModel.players.value?.get(0)?.name
                scoreBoardScores.text = " ${ticTacViewModel.players.value?.get(0)?.score}  X  ${
                    ticTacViewModel.players.value?.get(1)?.score
                } "
                scoreBoardPlayer2.text = ticTacViewModel.players.value?.get(1)?.name

                //calls clearCells when the moves property is empty (when game is resetted or when a new match starts)
                if (ticTacViewModel.players.value?.get(0)?.moves?.size == 0 && ticTacViewModel.players.value?.get(
                        1
                    )?.moves?.size == 0
                ) clearCells()
            }
        })


        //reset button logic
        binding?.resetButton?.setOnClickListener {
            ticTacViewModel.resetGameButton()
        }

        //stop button logic
        binding?.stopButton?.setOnClickListener {
            ticTacViewModel.stopGame()
        }


        //cell buttons logic; when clicked: adds cell's coordinate into player's move list, changes into X or O depending on turn variable, check if player won
        binding?.a1?.setOnClickListener {
            if (binding!!.a1.text == "") {
                mp.start()
                ticTacViewModel.addToMovesList("a1")
                binding!!.a1.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            };

        }
        binding?.a2?.setOnClickListener {
            if (binding!!.a2.text == "") {
                mp.start()
                ticTacViewModel.addToMovesList("a2")
                binding!!.a2.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
        binding?.a3?.setOnClickListener {
            if (binding!!.a3.text == "") {
                mp?.start()
                ticTacViewModel.addToMovesList("a3")
                binding!!.a3.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            };
        }
        binding?.b1?.setOnClickListener {
            if (binding!!.b1.text == "") {
                mp?.start()
                ticTacViewModel.addToMovesList("b1");
                binding!!.b1.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
        binding?.b2?.setOnClickListener {
            if (binding!!.b2.text == "") {
                mp?.start()
                ticTacViewModel.addToMovesList("b2");
                binding!!.b2.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
        binding?.b3?.setOnClickListener {
            if (binding!!.b3.text == "") {
                mp?.start()
                ticTacViewModel.addToMovesList("b3");
                binding!!.b3.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
        binding?.c1?.setOnClickListener {
            if (binding!!.c1.text == "") {
                mp?.start()
                ticTacViewModel.addToMovesList("c1");
                binding!!.c1.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
        binding?.c2?.setOnClickListener {
            if (binding!!.c2.text == "") {
                mp?.start()
                ticTacViewModel.addToMovesList("c2");
                binding!!.c2.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
        binding?.c3?.setOnClickListener {
            if (binding!!.c3.text == "") {
                mp?.start()
                ticTacViewModel.addToMovesList("c3");
                binding!!.c3.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
    }

    //clears all the cells, making the grid empty for starting a new match
    fun clearCells() {
        binding?.a1?.text = ""
        binding?.a2?.text = ""
        binding?.a3?.text = ""
        binding?.b1?.text = ""
        binding?.b2?.text = ""
        binding?.b3?.text = ""
        binding?.c1?.text = ""
        binding?.c2?.text = ""
        binding?.c3?.text = ""
    }
    var mp: MediaPlayer = MediaPlayer.create(activity, R.raw.click)
}
