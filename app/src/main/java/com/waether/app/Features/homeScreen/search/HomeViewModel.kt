package com.waether.app.Features.homeScreen.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.entities.City
import com.example.usecases.SearchCityByNameUseCase
import com.example.usecases.citiesResult
import com.example.usecases.toMutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.io.Serializable


class HomeViewModel(
    val showCityForecast: PublishSubject<Serializable> = PublishSubject.create(),
    private val disposables: CompositeDisposable = CompositeDisposable(),
    val searchProgress: MutableLiveData<Boolean> = false.toMutableLiveData(),
    val citiesResult: citiesResult = ArrayList<City>().toMutableLiveData(),
    private val searchCityByNameUseCase: SearchCityByNameUseCase = SearchCityByNameUseCase(searchProgress, citiesResult)
) : ViewModel() {

    fun onSearchButtonClicked(cityName: String?) {
        Observable.fromCallable { searchCityByNameUseCase.invoke(cityName) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) //not important due to presence of live data
            .subscribe().also { disposables.add(it) } //if we need to pass something we us carely brackets {code}
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
//typealias name= type

//:: meaning referring to method
