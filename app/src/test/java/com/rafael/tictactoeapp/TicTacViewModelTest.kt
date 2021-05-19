package com.rafael.tictactoeapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rafael.tictactoeapp.viewmodel.TicTacViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TicTacViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private var ticTacViewModel: TicTacViewModel = TicTacViewModel()

    @Test
    fun `given 2 input names, when createPlayers is called, then update live data for _players with the imputed data`() {

        //given
        val name1 = "Rafael"
        val name2 = "Daniel"


        //When
        ticTacViewModel.createPlayers(name1, name2)

        //Then

        Assert.assertTrue(
            ticTacViewModel.players.value?.get(0)!!.name == name1 &&
                    ticTacViewModel.players.value?.get(1)!!.name == name2
        )
    }

    @Test
    fun `given 2 input names, when createPlayers is called, then compare the result with a wrong input it should assert false`() {

        //given
        val name1 = "Rafael"
        val name2 = "Daniel"
        val wrongName = "123"


        //When
        ticTacViewModel.createPlayers(name1, name2)

        //Then

        Assert.assertFalse(
            ticTacViewModel.players.value?.get(0)!!.name == wrongName &&
                    ticTacViewModel.players.value?.get(1)!!.name == name2
        )
    }

}