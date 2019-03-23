package com.example.usecases

import android.arch.lifecycle.MutableLiveData
import com.example.entities.City
import com.example.entities.FavoriteCityId
import java.lang.Exception


fun numberIncrementer(liveData: MutableLiveData<Int>, incrementByValue: Int = 1) {
    val oldValue = liveData.value ?: 0
    liveData.postValue(oldValue + incrementByValue)
}

//use case 1 search cities by name
// if is searching don't trigger action
//cities name must not be null
//if all is ok trigger search
fun searchCitiesByName(
    searchName: String?,
    searching: MutableLiveData<Boolean>,
    result: MutableLiveData<List<City>>,
    repository: CitiesRepository = citiesRepository
) {

    searchName
        ?.takeUnless { searching.value ?: false }
        ?.takeUnless { it.isBlank() }
        ?.also { searching.postValue(true) }
        ?.let { repository.searchCitiesByName(it) }
        ?.also { result.postValue(it) }
        ?.also { searching.postValue(false) }
}

//use case 2:  retrieve favourite cities ids
//if is retrieving don't trigger action
//if favourites is empty through exception
//favourites is not empty convert them to ids(long)
fun retrieveFavouriteCitiesIds(
    isRetrieving: MutableLiveData<Boolean>,
    result: MutableLiveData<List<Long>>,
    repository: CitiesRepository = citiesRepository
) {

    repository
        .takeUnless { isRetrieving.value ?: false }
        ?.also { isRetrieving.postValue(true) }
        ?.let { it.retrieveFavouriteCitiesIds() }
        ?.takeIf { it.isEmpty() }
        ?.also { throw Exception() }
        ?.takeUnless { it.isEmpty() }
        ?.let { favouriteCityIdConverter(it) }
        ?.also { result.postValue(it) }
        ?.also {isRetrieving.postValue(false)}

}

// favouriteCityIdConverter
//input list of FavouriteCityId
//get ids (Long)
//add ids to list
//return the list of ids
fun favouriteCityIdConverter(cityIds: List<FavoriteCityId>): List<Long> {
    val cityIdsLong = mutableListOf<Long>()
    cityIds.let { it.forEach { cityIdsLong.add(it.id) } }
    return cityIdsLong
}

//use case 3: retrieve cities by ids
//if retrieving then then do not trigger action
//if all is ok trigger action
fun retrieveCitiesByIds(
    ids: List<Long>,
    isRetrieving: MutableLiveData<Boolean>,
    result: MutableLiveData<List<City>>,
    repository: CitiesRepository= citiesRepository
){
    ids
        .takeUnless { isRetrieving.value ?: false }
        ?.also { isRetrieving.postValue(true) }
        ?.let { repository.retrieveCitiesById(it) }
        ?.also { result.postValue(it) }
        ?.also { isRetrieving.postValue(false) }
}

