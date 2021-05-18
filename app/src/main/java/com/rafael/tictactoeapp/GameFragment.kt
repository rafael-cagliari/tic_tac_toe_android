package com.rafael.tictactoeapp

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rafael.tictactoeapp.databinding.GameFragmentBinding
import com.rafael.tictactoeapp.model.Match
import com.rafael.tictactoeapp.viewmodel.TicTacViewModel

enum class Sound {
    ON, OFF
}

var sound: Sound = Sound.ON

class GameFragment : Fragment() {

    private lateinit var ticTacViewModel: TicTacViewModel

    var binding: GameFragmentBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = GameFragmentBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        ticTacViewModel = ViewModelProvider(requireActivity()).get(TicTacViewModel::class.java)
        setHasOptionsMenu(true)
        return fragmentBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mp = MediaPlayer.create(activity?.baseContext, R.raw.click);


        //this function is called on VS AI mode (when the game_mode variable is set to "vs AI")
        //after player one plays, selecting a random cell
        fun computerPlay() {
            val handler = Handler()
            handler.postDelayed({
                if (ticTacViewModel.game_mode == "vs AI" && ticTacViewModel.turn == "player2") {
                    val coordinates = listOf("a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3")
                    val availableCoordinates =
                        coordinates.minus(ticTacViewModel.players.value!![0].moves)
                            .minus(ticTacViewModel.players.value!![1].moves)
                    val selectedCell = availableCoordinates.random()
                    when (selectedCell) {
                        "a1" -> binding?.a1?.text = ticTacViewModel.setCell()
                        "a2" -> binding?.a2?.text = ticTacViewModel.setCell()
                        "a3" -> binding?.a3?.text = ticTacViewModel.setCell()
                        "b1" -> binding?.b1?.text = ticTacViewModel.setCell()
                        "b2" -> binding?.b2?.text = ticTacViewModel.setCell()
                        "b3" -> binding?.b3?.text = ticTacViewModel.setCell()
                        "c1" -> binding?.c1?.text = ticTacViewModel.setCell()
                        "c2" -> binding?.c2?.text = ticTacViewModel.setCell()
                        "c3" -> binding?.c3?.text = ticTacViewModel.setCell()
                    }
                    if (sound == Sound.ON) mp?.start()
                    ticTacViewModel.addToMovesList(selectedCell);

                    //created this line for clearing the message for the AI, since calling the clearMessage()
                    //function calls notifyObserver, causing bugs
                    ticTacViewModel.checkWin()
                    if (ticTacViewModel.game_message != "") ticTacViewModel.game_message = ""
                }},500)
        }

        //ViewModel observer
        ticTacViewModel.players.observe(viewLifecycleOwner, Observer {

            computerPlay()

            binding!!.apply {


                //updates with a message everytime a player wins or there is a draw
                binding?.gameMessage?.text = ticTacViewModel.game_message

                //make the player's name change color during their turn
                if (ticTacViewModel.turn == "player1") {
                    scoreBoardPlayer1.setTextColor(resources.getColor(R.color.purple_700)); scoreBoardPlayer2.setTextColor(
                        resources.getColor(R.color.black)
                    )
                }
                if (ticTacViewModel.turn == "player2") {
                    scoreBoardPlayer2.setTextColor(resources.getColor(R.color.purple_700)); scoreBoardPlayer1.setTextColor(
                        resources.getColor(R.color.black)
                    )
                }

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
                ) {clearCells()}
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

        //sound button
        binding?.volumeButton?.setOnClickListener {
            soundSwitch()
        }

        //cell buttons logic; when clicked: adds cell's coordinate into player's move list, changes into X or O depending on turn variable, check if player won
        binding?.a1?.setOnClickListener {

            if (binding!!.a1.text == "") {
                if (sound == Sound.ON) mp.start()
                ticTacViewModel.clearMessage()
                ticTacViewModel.addToMovesList("a1")
                binding!!.a1.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }

        binding?.a2?.setOnClickListener {
            if (binding!!.a2.text == "") {
                if (sound == Sound.ON) mp?.start()
                ticTacViewModel.clearMessage()
                ticTacViewModel.addToMovesList("a2")
                binding!!.a2.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }

        binding?.a3?.setOnClickListener {
            if (binding!!.a3.text == "") {
                if (sound == Sound.ON) mp?.start()
                ticTacViewModel.clearMessage()
                ticTacViewModel.addToMovesList("a3")
                binding!!.a3.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }

        binding?.b1?.setOnClickListener {
            if (binding!!.b1.text == "") {
                if (sound == Sound.ON) mp?.start()
                ticTacViewModel.clearMessage()
                ticTacViewModel.addToMovesList("b1");
                binding!!.b1.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }

        binding?.b2?.setOnClickListener {
            if (binding!!.b2.text == "") {
                if (sound == Sound.ON) mp?.start()
                ticTacViewModel.clearMessage()
                ticTacViewModel.addToMovesList("b2");
                binding!!.b2.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
        binding?.b3?.setOnClickListener {
            if (binding!!.b3.text == "") {
                if (sound == Sound.ON) mp?.start()
                ticTacViewModel.clearMessage()
                ticTacViewModel.addToMovesList("b3");
                binding!!.b3.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }

        binding?.c1?.setOnClickListener {
            if (binding!!.c1.text == "") {
                if (sound == Sound.ON) mp?.start()
                ticTacViewModel.clearMessage()
                ticTacViewModel.addToMovesList("c1");
                binding!!.c1.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }
        binding?.c2?.setOnClickListener {
            if (binding!!.c2.text == "") {
                if (sound == Sound.ON) mp?.start()
                ticTacViewModel.clearMessage()
                ticTacViewModel.addToMovesList("c2");
                binding!!.c2.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }

        binding?.c3?.setOnClickListener {
            if (binding!!.c3.text == "") {
                if (sound == Sound.ON) mp?.start()
                ticTacViewModel.clearMessage()
                ticTacViewModel.addToMovesList("c3");
                binding!!.c3.text = ticTacViewModel.setCell()
                ticTacViewModel.checkWin()
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.match_history, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_match_history){
           findNavController().navigate(R.id.action_gameFragment_to_matchHistoryFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    //clears all the cells, making the grid empty for starting a new match
    private fun clearCells() {
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


    private fun soundSwitch() {
        sound = when (sound) {
            Sound.ON -> {
                binding?.volumeButton?.setImageResource(R.drawable.ic_baseline_volume_off); Sound.OFF
            }
            Sound.OFF -> {
                binding?.volumeButton?.setImageResource(R.drawable.ic_baseline_volume); Sound.ON
            }
        }
    }

}


