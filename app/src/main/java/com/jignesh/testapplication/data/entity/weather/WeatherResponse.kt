package com.jignesh.testapplication.data.entity.weather

import com.google.gson.annotations.SerializedName
import com.jignesh.testapplication.domain.models.base.ResponseObject
import com.jignesh.testapplication.domain.models.weather.Weather

class WeatherResponse (
    @SerializedName("main")val mainDataResponse : MainDataResponse = MainDataResponse(),
    @SerializedName("name")val name : String = ""
): ResponseObject<Weather> {

    override fun toAppDomain(): Weather {
        val weather: Weather = Weather(
            mainDataResponse.temp,
            mainDataResponse.humidity,
            mainDataResponse.pressure,
            mainDataResponse.temp_min,
            mainDataResponse.temp_max,
            name
        )

        return weather
    }


    class MainDataResponse(
        @SerializedName("temp")val temp : String = "",
        @SerializedName("humidity")val humidity : String = "",
        @SerializedName("pressure")val pressure : String = "",
        @SerializedName("temp_min")val temp_min : String = "",
        @SerializedName("temp_max")val temp_max : String = ""
    ){

    }

}