package com.jignesh.testapplication.data.repository.implementation.api

import arrow.core.Either
import com.jignesh.testapplication.data.net.WeatherApiClient
import com.jignesh.testapplication.data.repository.base.BaseRepository
import com.jignesh.testapplication.data.repository.datasources.api.weather.WeatherApi
import com.jignesh.testapplication.domain.models.base.Failure
import com.jignesh.testapplication.domain.models.weather.Weather
import com.jignesh.testapplication.domain.repository.WeatherRepository
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val weatherApiClient: WeatherApiClient
) : BaseRepository(), WeatherRepository {


    override fun getWeather(lat: String, lon: String, appid: String): Observable<Either<Failure, Weather>> =
        modifyObservable(weatherApiClient.api.getWeather(lat,lon,appid))

    override fun getFiveDaysWeather(lat: String, lon: String, appid: String): Observable<Either<Failure, List<Weather>>> =
        modifyObservable(weatherApiClient.api.getFiveDaysWeather(lat,lon,appid))

}