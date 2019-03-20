package com.example.usecases.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Entity
import android.arch.persistence.room.RoomDatabase
import com.example.entities.City
import com.example.entities.FavoriteCityId


@Database(entities = [City::class,FavoriteCityId::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {


    abstract fun citiesDao(): City

    abstract fun favouriteCitiesDao(): FavouriteCitiesDao

}