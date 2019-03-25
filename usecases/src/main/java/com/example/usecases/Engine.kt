package com.example.usecases

import android.arch.lifecycle.MutableLiveData

//MutableLiveData extesions

fun <T> T.toMutableLiveData(): MutableLiveData<T> {
    return MutableLiveData<T>().also { it.value = this }
}