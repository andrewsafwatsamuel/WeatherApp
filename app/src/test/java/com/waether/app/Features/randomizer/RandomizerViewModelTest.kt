package com.waether.app.Features.randomizer

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


class RandomizerViewModelTest {

    @JvmField
    @Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `init Then Update NumberLiveData Value To Zero`() {
        //arrange
        val randomizerViewModel = RandomizerViewModel()
        val result = randomizerViewModel.numberLiveData.value

        //assert
        Assert.assertTrue(result == DEFAULT_VALUE)

        //initial value of numberLiveData is DEFAULT_VALUE

    }

    @Test
    fun `increment Number When NumberLiveData Is Zero Then Update LiveData Value To One`() {

        //arrange
        val randomizerViewModel = RandomizerViewModel()
        randomizerViewModel.numberLiveData.value = 0

        //act
        randomizerViewModel.incrementNumber()
        //assert
        val result = randomizerViewModel.numberLiveData.value
        Assert.assertTrue(result == 1)
        //when I invoke incrementNumber(), numberLiveData is incremented by 1
    }


}