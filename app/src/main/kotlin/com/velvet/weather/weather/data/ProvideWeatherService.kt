package com.velvet.weather.weather.data

import com.github.johnnysc.coremvvm.data.MakeService
import com.github.johnnysc.coremvvm.data.ProvideRetrofitBuilder
import com.velvet.weather.addcity.data.ProvideGeoService

interface ProvideWeatherService {

    fun getService(): WeatherService

    class Base(
        retrofitBuilder: ProvideRetrofitBuilder
    ) : MakeService.Abstract(
        retrofitBuilder
    ), ProvideWeatherService {

        override fun getService(): WeatherService = service(WeatherService::class.java)

        override fun baseUrl(): String = "https://api.openweathermap.org/data/2.5/"
    }
}