package com.example.usecases

import android.app.Application
import android.arch.lifecycle.MutableLiveData

internal val applicationLiveData = MutableLiveData<Application>()

internal fun  MutableLiveData<Application>.getApplication() = value!!

object Domain {
    fun with(application: Application) {
        applicationLiveData.value = application
        applicationLiveData.getApplication()
    }
}