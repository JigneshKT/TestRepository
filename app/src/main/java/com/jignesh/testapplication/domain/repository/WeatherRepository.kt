package com.jignesh.testapplication.domain.repository

import arrow.core.Either
import com.jignesh.testapplication.domain.models.base.Failure
import com.jignesh.testapplication.domain.models.weather.Weather
import io.reactivex.Observable

interface WeatherRepository{
    fun getWeather(lat: String, lon: String, appid: String): Observable<Either<Failure, Weather>>
    fun getFiveDaysWeather(lat: String, lon: String, appid: String): Observable<Either<Failure, List<Weather>>>
}