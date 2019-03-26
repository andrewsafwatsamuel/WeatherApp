package com.example.usecases

import com.example.entities.City
import com.example.entities.Coordinates
import com.example.entities.FavoriteCityId

interface Repository {
    fun searchCitiesByName(city: String): List<City>


    fun retrieveFavouriteCitiesIds(): List<FavoriteCityId>

    fun retrieveCitiesById(favouriteCityIds: List<Long>): List<City>

    fun addFavouriteCityId(favouriteCityId: FavoriteCityId)

    fun removeFavouriteCityId(favouriteCityId: FavoriteCityId)
}


val cities = listOf(
    City(1L, "indiana", "America", Coordinates(1.1, 1.1)),
    City(2L, "Aswan", "Egypt", Coordinates(1.1, 1.1)),
    City(3L, "Cairo", "Egypt", Coordinates(1.1, 1.1)),
    City(4L, "Marakesh", "Moroco", Coordinates(1.1, 1.1)),
    City(5L, "Saint-Lewis", "America", Coordinates(1.1, 1.1)),
    City(6L, "California", "America", Coordinates(1.1, 1.1)),
    City(7L, "Newyourk", "America", Coordinates(1.1, 1.1)),
    City(8L, "Alberta", "Canada", Coordinates(1.1, 1.1)),
    City(9L, "New delhi", "India", Coordinates(1.1, 1.1)),
    City(10L, "Baghdad", "Iraq", Coordinates(1.1, 1.1))

)

val favouriteCitiesIds = mutableListOf(FavoriteCityId(1L), FavoriteCityId(2L), FavoriteCityId(5L))

val emptyFavouriteList= listOf<FavoriteCityId>()




open class FakeRepositoryImplementer : Repository {
    override fun searchCitiesByName(city: String): List<City> {
        return listOf()
    }

    override fun retrieveFavouriteCitiesIds(): List<FavoriteCityId> {
        return listOf()
    }

    override fun retrieveCitiesById(favouriteCityIds: List<Long>): List<City> {
        return listOf()
    }

    override fun addFavouriteCityId(favouriteCityId: FavoriteCityId) {
    }

    override fun removeFavouriteCityId(favouriteCityId: FavoriteCityId) {
    }
}

/*




override fun addFavouriteCityId(favouriteCityId: FavoriteCityId) {
    favouriteCitiesIds.add(favouriteCityId)
}

override fun removeFavouriteCityId(favouriteCityId: FavoriteCityId) {
    favouriteCitiesIds.remove(favouriteCityId)
}*/
