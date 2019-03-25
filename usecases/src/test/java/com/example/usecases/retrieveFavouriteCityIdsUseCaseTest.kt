package com.example.usecases

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.example.entities.FavoriteCityId
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.lang.Exception


//use case 2:  retrieve favourite cities ids
//if is retrieving don't trigger action
//if favourites is empty through exception
//favourites is not empty convert them to ids(long)??
//if all is ok retrieve

class retrieveFavouriteCityIdsUseCaseTest {

    @JvmField
    @Rule
    val rule = InstantTaskExecutorRule()

    //if all is ok retrieve
    @Test
    fun `retrieve when all is ok return results`() {

        //arrange
        val isRetrieving = MutableLiveData<Boolean>()
        val result = MutableLiveData<List<Long>>()
        val repository = FavouriteCityIdsRepositoryMock()
        val retrieveFavouriteCityIdsUseCase = RetrieveFavouriteCityIdsUseCase(isRetrieving, result, repository)
        //act
        retrieveFavouriteCityIdsUseCase()
        //assert
        assertTrue(result.value!!.isNotEmpty())
    }

    //if favourites is empty through exception
    @Test(expected = Exception::class)
    fun `retrieve when FavouriteCities list is empty throw exception`() {
        //arrange
        val isRetrieving = MutableLiveData<Boolean>()
        val result = MutableLiveData<List<Long>>()
        val repository = FavouriteCityIdsEmptyListRepositoryMock()
        val retrieveFavouriteCityIdsUseCase = RetrieveFavouriteCityIdsUseCase(isRetrieving, result, repository)
        //act
        retrieveFavouriteCityIdsUseCase()
    }

    //if is retrieving don't trigger action
    @Test
    fun `retrieve when isRetrieving don't return results`() {

        //arrange
        val isRetrieving = MutableLiveData<Boolean>()
        val result = MutableLiveData<List<Long>>()
        val repository = FavouriteCityIdsRepositoryMock()
        val retrieveFavouriteCityIdsUseCase = RetrieveFavouriteCityIdsUseCase(isRetrieving, result, repository)
        //act
        isRetrieving.postValue(true)
        retrieveFavouriteCityIdsUseCase()
        //assert
        assertTrue(result.value==null)
    }


}

class FavouriteCityIdsRepositoryMock : FakeRepositoryImplementer() {
    override fun retrieveFavouriteCitiesIds(): List<FavoriteCityId> {
        return favouriteCitiesIds
    }
}

class FavouriteCityIdsEmptyListRepositoryMock : FakeRepositoryImplementer() {
    override fun retrieveFavouriteCitiesIds(): List<FavoriteCityId> {
        return emptyFavouriteList
    }
}