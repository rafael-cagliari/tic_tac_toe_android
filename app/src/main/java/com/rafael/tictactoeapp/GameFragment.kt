package com.rafael.tictactoeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  binding!!.scoreBoard.text = "oie"
        Toast.makeText(context, ticTacViewModel.players.value?.get(0)?.name, Toast.LENGTH_SHORT)
            .show()

        ticTacViewModel.players.observe(viewLifecycleOwner, Observer {
            binding!!.apply {
                scoreBoardPlayer1.text = ticTacViewModel.players.value?.get(0)?.name
                scoreBoardScores.text = " ${ticTacViewModel.players.value?.get(0)?.score}  X  ${
                    ticTacViewModel.players.value?.get(1)?.score
                } "
                scoreBoardPlayer2.text = ticTacViewModel.players.value?.get(1)?.name
                if (ticTacViewModel.players.value?.get(0)?.moves?.size == 0 && ticTacViewModel.players.value?.get(
                        1
                    )?.moves?.size == 0
                ) clearCells()
            }
        })

        binding?.a1?.setOnClickListener {
            if (binding!!.a1.text == "") {
                ticTacViewModel.addToMovesList("a1")
                binding!!.a1.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            };

        }
        binding?.a2?.setOnClickListener {
            if (binding!!.a2.text == "") {
                ticTacViewModel.addToMovesList("a2")
                binding!!.a2.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
        binding?.a3?.setOnClickListener {
            if (binding!!.a3.text == "") {
                ticTacViewModel.addToMovesList("a3")
                binding!!.a3.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            };
        }
        binding?.b1?.setOnClickListener {
            if (binding!!.b1.text == "") {
                ticTacViewModel.addToMovesList("b1");
                binding!!.b1.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
        binding?.b2?.setOnClickListener {
            if (binding!!.b2.text == "") {
                ticTacViewModel.addToMovesList("b2");
                binding!!.b2.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
        binding?.b3?.setOnClickListener {
            if (binding!!.b3.text == "") {
                ticTacViewModel.addToMovesList("b3");
                binding!!.b3.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
        binding?.c1?.setOnClickListener {
            if (binding!!.c1.text == "") {
                ticTacViewModel.addToMovesList("c1");
                binding!!.c1.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
        binding?.c2?.setOnClickListener {
            if (binding!!.c2.text == "") {
                ticTacViewModel.addToMovesList("c2");
                binding!!.c2.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
        binding?.c3?.setOnClickListener {
            if (binding!!.c3.text == "") {
                ticTacViewModel.addToMovesList("c3");
                binding!!.c3.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
    }

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
}
