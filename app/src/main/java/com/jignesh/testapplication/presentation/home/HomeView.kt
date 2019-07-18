package com.jignesh.testapplication.presentation.home

import android.location.Location
import com.jignesh.testapplication.domain.models.weather.Weather
import com.jignesh.testapplication.presentation.base.View

interface HomeView : View {
    fun getWeatherSuccess(weather: Weather)
    fun getWeatherFailure()

    fun getFiveDaysWeatherSuccess(fiveDaysWeather: List<Weather>)
    fun getFiveDaysWeatherFailure()

    fun onLocationSuccess(location: Location)
    fun onLocationFailure()
}