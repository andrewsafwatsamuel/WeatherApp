package com.waether.app.Features.randomizer

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.usecases.numberIncrementer

 const val DEFAULT_VALUE = 0

class RandomizerViewModel : ViewModel() {




    val numberLiveData = MutableLiveData<Int>()


    init {
        numberLiveData.postValue(DEFAULT_VALUE) }


    fun incrementNumber() {
        numberIncrementer(numberLiveData)
    }




}