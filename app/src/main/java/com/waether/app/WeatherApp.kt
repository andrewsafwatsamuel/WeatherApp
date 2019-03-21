package com.waether.app

import android.app.Application
import com.example.usecases.Domain
import com.waether.app.core.activityLifeCycleCallBacks

class WeatherApp:Application(){
    override fun onCreate() {
        super.onCreate()
      //  registerActivityLifecycleCallbacks(activityLifeCycleCallBacks)
        Domain.with(this)
    }
}