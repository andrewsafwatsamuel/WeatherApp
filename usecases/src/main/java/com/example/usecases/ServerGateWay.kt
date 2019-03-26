package com.example.usecases

import com.example.entities.ForecastsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

private const val SERVER_BASE_URL="api.openweathermap.org/"

interface ServerApis{

@GET("data/2.5/forecast")
fun getCityForeCast(@Query("id")cityId:String):Single<ForecastsResponse>

}