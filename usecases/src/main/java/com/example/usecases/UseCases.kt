package com.example.usecases

import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.TypeConverter
import com.example.entities.Coordinates
import com.google.gson.Gson


fun numberIncrementer(liveData:MutableLiveData<Int>,incrementByValue:Int=1){

val oldValue=liveData.value?:0
    liveData.postValue(oldValue+incrementByValue)


    class CoordinatesTypeConverter{
        @TypeConverter
        fun toJson(coordinates: Coordinates)= Gson().toJson(coordinates)

        @TypeConverter
        fun fromJson(string: String)= Gson().fromJson(string, Coordinates::class.java)

    }

}