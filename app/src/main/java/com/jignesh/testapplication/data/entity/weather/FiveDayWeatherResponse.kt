package com.jignesh.testapplication.data.entity.weather

import com.google.gson.annotations.SerializedName
import com.jignesh.testapplication.domain.models.base.ResponseObject
import com.jignesh.testapplication.domain.models.weather.Weather

class FiveDaysWeatherResponse (
    @SerializedName("list")val responseList : List<ResponseList>  =  listOf()
): ResponseObject<List<Weather>> {

    override fun toAppDomain(): List<Weather> {
        val weatherList = mutableListOf<Weather>()
        with(responseList){
            forEach{
                weatherList.add(
                    Weather(
                        it.mainDataResponse.temp,
                        it.mainDataResponse.humidity,
                        it.mainDataResponse.pressure,
                        it.mainDataResponse.temp_min,
                        it.mainDataResponse.temp_max,
                        it.name
                    )
                )
            }
        }
        return weatherList
    }

    class ResponseList (
        @SerializedName("main") val mainDataResponse: MainDataResponse = MainDataResponse(),
        @SerializedName("name")val name : String = ""
    ) {

        class MainDataResponse(
            @SerializedName("temp") val temp: String = "",
            @SerializedName("humidity") val humidity: String = "",
            @SerializedName("pressure") val pressure: String = "",
            @SerializedName("temp_min") val temp_min: String = "",
            @SerializedName("temp_max") val temp_max: String = ""
        ) {

        }
    }

}