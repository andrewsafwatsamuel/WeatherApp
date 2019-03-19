package com.example.usecases

import android.arch.lifecycle.MutableLiveData


fun numberIncrementer(liveData:MutableLiveData<Int>,incrementByValue:Int=1){

val oldValue=liveData.value?:0
    liveData.postValue(oldValue+incrementByValue)

}