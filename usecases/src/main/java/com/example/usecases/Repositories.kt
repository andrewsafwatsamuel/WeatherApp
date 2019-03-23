package com.example.usecases

import com.example.entities.City
import com.example.entities.FavoriteCityId
import com.example.usecases.database.WeatherDatabase
import com.example.usecases.database.weatherDatabase

val citiesRepository by lazy { CitiesRepository() }

class CitiesRepository(private val database:  WeatherDatabase= weatherDatabase) {

    fun searchCitiesByName(city: String) = database.citiesDao.queryCitiesByName(city)


    fun retrieveFavouriteCitiesIds() = database.favouriteCitiesDao.QueryAll()

    fun retrieveCitiesById(favouriteCityIds: List<Long>) = database.citiesDao.queryCityIds(favouriteCityIds)

    fun addFavouriteCityId(favouriteCityId: FavoriteCityId) =
        database.favouriteCitiesDao.insert(favouriteCityId)

    fun removeFavouriteCityId(favouriteCityId: FavoriteCityId) =
        database.favouriteCitiesDao.delete(favouriteCityId)

}