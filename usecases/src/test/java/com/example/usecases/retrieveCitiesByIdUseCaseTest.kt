package com.example.usecases

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.example.entities.City
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

//use case 3: retrieve cities by ids
//if retrieving then then do not trigger action
//if all is ok trigger action

class retrieveCitiesByIdUseCaseTest {
    @JvmField
    @Rule
    val rule = InstantTaskExecutorRule()

    //if all is ok trigger action
    @Test
    fun `retrieve when all is ok return results`() {
        //arrange
        val isRetrieving = MutableLiveData<Boolean>()
        val result = MutableLiveData<List<City>>()
        val repository = RetrieveCitiesByIdUseCaseRepositoryMock()
        val retrieveCitiesByIdUseCase = RetrieveCitiesByIdUseCase(isRetrieving, result, repository)

        //act
        retrieveCitiesByIdUseCase(listOf(1L,2L,5L))

        //assert
        assertTrue(result.value!=null)
    }

    //if retrieving then then do not trigger action
    @Test
    fun `retrieve when isRetrieving  do not return results`() {
        //arrange
        val isRetrieving = MutableLiveData<Boolean>()
        val result = MutableLiveData<List<City>>()
        val repository = RetrieveCitiesByIdUseCaseRepositoryMock()
        val retrieveCitiesByIdUseCase = RetrieveCitiesByIdUseCase(isRetrieving, result, repository)

        //act
        isRetrieving.postValue(true)
        retrieveCitiesByIdUseCase(listOf(1L,2L,5L))

        //assert
        assertTrue(result.value==null)
    }



}

class RetrieveCitiesByIdUseCaseRepositoryMock : FakeRepositoryImplementer() {
    override fun retrieveCitiesById(favouriteCityIds: List<Long>): List<City> {
        val result = mutableListOf<City>()
        favouriteCityIds.forEach { getFavouriteCities(it, cities, result) }
        return result
    }

    fun getFavouriteCities(id: Long, citiesList: List<City>, result: MutableList<City>) {
        for (city in citiesList) {
            if (city.id == id) {
                result.add(city)
            }
        }
    }
}