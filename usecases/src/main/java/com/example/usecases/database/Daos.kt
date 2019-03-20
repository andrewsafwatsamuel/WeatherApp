package com.example.usecases.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.entities.City

@Dao
interface CitiesDao {
    @Query("SELECT * FROM city WHERE name LIKE :name")
    fun queryCitiesByName(name: String): List<City>

    @Query("SELECT * FROM city WHERE id IN (:ids)")
    fun queryCityIds(ids: List<Long>): List<Long>
}

@Dao
interface FavouriteCitiesDao {
    @Query("SELECT * FROM FavoriteCityId")
    fun QueryAll(): List<Long>
}