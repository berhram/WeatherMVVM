package com.velvet.weather.weather.data

import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError
import com.velvet.weather.Settings.API_KEY
import com.velvet.weather.weather.data.schemas.City
import com.velvet.weather.weather.data.schemas.Forecast

interface WeatherCloudDataSource {

    companion object {

        private const val EXCLUDED = "minutely, alerts, hourly, daily"
        private const val UNITS = "metric"
    }

    suspend fun getWeather(city: City): Forecast

    class Base(
        private val weatherService: WeatherService,
        handleError: HandleError
    ) : CloudDataSource.Abstract(handleError), WeatherCloudDataSource {

        override suspend fun getWeather(city: City): Forecast = handle {
            weatherService.getWeatherForecast(
                appId = API_KEY,
                latitude = city.latitude.toString(),
                longitude = city.longitude.toString(),
                exclude = EXCLUDED,
                units = UNITS
            )
        }
    }
}