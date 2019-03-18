package com.waether.app.Features.randomizer

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class RandomizerViewModel:ViewModel(){

    val numberLiveData=MutableLiveData<Int>()

    init {
        numberLiveData.postValue(1)
    }

    override fun onCleared() {
        super.onCleared()
    }

}