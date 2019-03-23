package com.example.usecases.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.entities.City
import com.example.entities.FavoriteCityId

@Dao
interface CitiesDao {

    @Query("SELECT * FROM City")
    fun queryAll():List<City>

    @Query("SELECT * FROM city WHERE name LIKE '%' ||:name|| '%'")
    fun queryCitiesByName(name: String): List<City>

    @Query("SELECT * FROM city WHERE id IN (:ids)")
    fun queryCityIds(ids: List<Long>): List<City>
}

@Dao
interface FavouritesDao {
    @Query("SELECT * FROM FavoriteCityId")
    fun QueryAll(): List<FavoriteCityId>

    @Insert
    fun insert(fovouriteCityIds: FavoriteCityId)

    @Delete
    fun delete(fovouriteCityIds:FavoriteCityId)
}