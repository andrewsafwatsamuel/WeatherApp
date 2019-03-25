package com.example.usecases

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.example.entities.City
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class SearchCityByNameUseCaseTest{
    @JvmField
    @Rule
    val rule = InstantTaskExecutorRule()

    //use case 1 search cities by name
    // if is searching don't trigger action
    //cities name must not be null
    //if all is ok trigger search


    //if all is ok trigger search
    @Test
    fun `searchCitiesByName with valid city name and successful response then update result`() {

        //arrange
        val searchName = "indiana"
        val isSearching = MutableLiveData<Boolean>()
        val repository = SearchCityByNameRepositoryMock()
        val result = MutableLiveData<List<City>>()
        val searchCityByNameUseCase=SearchCityByNameUseCase(searching=isSearching,result = result,repository = repository)

        //act
        isSearching.postValue(false)
        searchCityByNameUseCase(searchName)
        //assert
        assertTrue(result.value!!.isNotEmpty())

    }

    //cities name must not be null
    @Test
    fun `searchCitiesByName with city name = null do not update result`() {
        //arrange
        val searchName: String? = null
        val isSearching = MutableLiveData<Boolean>()
        val repository = SearchCityByNameRepositoryMock()
        val result = MutableLiveData<List<City>>()
        val searchCityByNameUseCase=SearchCityByNameUseCase(searching=isSearching,result = result,repository = repository)
        //act
        isSearching.postValue(false)
        searchCityByNameUseCase(searchName)
        //assert
        assertTrue(result.value == null)

    }

    // if is searching don't trigger action
    @Test
    fun `searchCitiesByName When IsSearching Value True Do Not Update Result`() {
        //arrange
        val searchName = "indiana"
        val isSearching = MutableLiveData<Boolean>()
        val repository = SearchCityByNameRepositoryMock()
        val result = MutableLiveData<List<City>>()
        val searchCityByNameUseCase=SearchCityByNameUseCase(searching=isSearching,result = result,repository = repository)
        //act
        isSearching.postValue(true)
        searchCityByNameUseCase(searchName)
        //assert
        assertTrue(result.value == null)
    }

    //cities name must not be blanck
    @Test
    fun `searchCitiesByName When city name value is blank Do Not Update Result`() {
        //arrange
        val searchName = ""
        val isSearching = MutableLiveData<Boolean>()
        val repository = SearchCityByNameRepositoryMock()
        val result = MutableLiveData<List<City>>()
        val searchCityByNameUseCase=SearchCityByNameUseCase(searching=isSearching,result = result,repository = repository)
        //act
        searchCityByNameUseCase(searchName)
        //assert
        assertTrue(result.value == null)
    }

}

class SearchCityByNameRepositoryMock:FakeRepositoryImplementer(){
    override fun searchCitiesByName(city: String): List<City> {
        val result = mutableListOf<City>()

        city.takeUnless { it.isEmpty() }
            ?.let { fillCitiesList(cities, result, city) }

        return result
    }
    fun fillCitiesList(listMain: List<City>, result: MutableList<City>, city: String) {
        for (item in listMain) {
            if (item.name!!.contains(city)) {
                result.add(item)
            }
        }
    }
}