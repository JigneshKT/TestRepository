package com.jignesh.testapplication.domain.usecase.weather

import com.jignesh.testapplication.domain.extensions.subObs
import com.jignesh.testapplication.domain.models.weather.Weather
import com.jignesh.testapplication.domain.repository.WeatherRepository
import com.jignesh.testapplication.domain.usecase.base.UseCase
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) : UseCase<Weather, GetWeatherUseCase.Params> {

    override fun invoke(params: Params) =
        weatherRepository.getWeather(params.lat, params.lon, params.appid).subObs()

    class Params(val lat: String = "",
                 val lon: String = "",
                 val appid : String = "")

}