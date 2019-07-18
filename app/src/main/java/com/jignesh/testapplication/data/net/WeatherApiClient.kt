package com.jignesh.testapplication.data.net

import com.jignesh.testapplication.data.repository.datasources.api.weather.WeatherApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class WeatherApiClient @Inject constructor(baseHttpClient: BaseHttpClient) {

    val BASE_URL: String = "https://api.openweathermap.org"


    val api: WeatherApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(baseHttpClient.getClient())
        .build()
        .create(WeatherApi::class.java)

}