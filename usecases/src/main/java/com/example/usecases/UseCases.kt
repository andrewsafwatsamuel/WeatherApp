package com.example.usecases

import android.arch.lifecycle.MutableLiveData
import com.example.entities.City
import com.example.entities.FavoriteCityId
import java.lang.Exception


fun numberIncrementer(liveData: MutableLiveData<Int>, incrementByValue: Int = 1) {
    val oldValue = liveData.value ?: 0
    liveData.postValue(oldValue + incrementByValue)
}


typealias citiesResult = MutableLiveData<List<City>>

class SearchCityByNameUseCase(
    private val searching: MutableLiveData<Boolean>,
    private val result: citiesResult,
    private val repository: Repository = citiesRepository
) {
    operator fun invoke(seachName: String?) {
        seachName
            ?.takeUnless { searching.value ?: false }
            ?.takeUnless { it.isBlank() }
            ?.also { searching.postValue(true) }
            ?.let { repository.searchCitiesByName(it) }
            ?.also { result.postValue(it) }
            ?.also { searching.postValue(false) }
    }
}


class RetrieveFavouriteCityIdsUseCase(
    private val isRetrieving: MutableLiveData<Boolean>,
    private val result: MutableLiveData<List<Long>>,
    private val repository: Repository = citiesRepository
) {
    operator fun invoke() {
        repository.takeUnless { isRetrieving.value ?: false }
            ?.also { isRetrieving.postValue(true) }
            ?.let { it.retrieveFavouriteCitiesIds() }
            ?.ifEmpty { throw Exception() }
            ?.map { it.id }
            ?.also { result.postValue(it) }
            ?.also { isRetrieving.postValue(false) }
    }

}


class RetrieveCitiesByIdUseCase(
    private val isRetrieving: MutableLiveData<Boolean>,
    private val result: MutableLiveData<List<City>>,
    private val repository: Repository = citiesRepository
) {
    operator fun invoke(ids: List<Long>) {
        ids.takeUnless { isRetrieving.value ?: false }
            ?.also { isRetrieving.postValue(true) }
            ?.let { repository.retrieveCitiesById(it) }
            ?.also { result.postValue(it) }
            ?.also { isRetrieving.postValue(false) }
    }
}



