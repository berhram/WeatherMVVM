package com.velvet.weather.weather.data

import com.velvet.weather.weather.data.schemas.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("onecall?")
    suspend fun getWeatherForecast(
        @Query("appid") appId: String,
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("exclude") exclude: String,
        @Query("units") units: String
    ): Forecast
}