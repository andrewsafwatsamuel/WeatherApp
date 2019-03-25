package com.example.usecases

import com.example.entities.City
import com.example.entities.FavoriteCityId
import com.example.usecases.database.WeatherDatabase
import com.example.usecases.database.weatherDatabase

val citiesRepository by lazy { CitiesRepository() }

class CitiesRepository(private val database:  WeatherDatabase= weatherDatabase):Repository {

    override fun searchCitiesByName(cityName: String) = database.citiesDao.queryCitiesByName(cityName)


    override fun retrieveFavouriteCitiesIds() = database.favouriteCitiesDao.QueryAll()

    override fun retrieveCitiesById(favouriteCityIds: List<Long>) = database.citiesDao.queryCityIds(favouriteCityIds)

    override fun addFavouriteCityId(favouriteCityId: FavoriteCityId) =
        database.favouriteCitiesDao.insert(favouriteCityId)

    override fun removeFavouriteCityId(favouriteCityId: FavoriteCityId) =
        database.favouriteCitiesDao.delete(favouriteCityId)

}