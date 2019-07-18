package com.jignesh.testapplication.data.repository.datasources.api.weather

import com.jignesh.testapplication.data.entity.weather.FiveDaysWeatherResponse
import com.jignesh.testapplication.data.entity.weather.WeatherResponse
import com.jignesh.testapplication.domain.models.weather.Weather
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather")
    fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String
    ): Observable<Result<WeatherResponse>>

    @GET("data/2.5/forecast")
    fun getFiveDaysWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String
    ): Observable<Result<FiveDaysWeatherResponse>>
}