package com.jignesh.testapplication.domain.usecase.weather

import com.jignesh.testapplication.domain.extensions.subObs
import com.jignesh.testapplication.domain.models.weather.Weather
import com.jignesh.testapplication.domain.repository.WeatherRepository
import com.jignesh.testapplication.domain.usecase.base.UseCase
import javax.inject.Inject

class GetFiveDaysWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) : UseCase<List<Weather>, GetFiveDaysWeatherUseCase.Params> {

    override fun invoke(params: Params) =
        weatherRepository.getFiveDaysWeather(params.lat, params.lon, params.appid).subObs()

    class Params(val lat: String = "",
                 val lon: String = "",
                 val appid : String = "")

}