package com.velvet.weather.weather.data

import com.velvet.weather.weather.data.schemas.City
import com.velvet.weather.weather.domain.WeatherDomain

class BaseWeatherRepository(
    private val cacheDataSource: WeatherCacheDataSource,
    private val cloudDataSource: WeatherCloudDataSource,
) : WeatherRepository {

    override suspend fun updateWeather(): WeatherDomain {
        var date: Long? = null
        val output = mutableListOf<Pair<City, Double>>()
        cacheDataSource.getCities().forEach {
            val result = cloudDataSource.getWeather(it)
            date = result.currentWeather.time
            output.add(it to result.currentWeather.temp)
        }
        return WeatherDomain.Base(date = date, list = output)
    }

    override suspend fun getSaved(): WeatherDomain {
        val output =
            cacheDataSource.getCities().map {
                it to null
            }
        return WeatherDomain.Base(
            date = null, list = output
        )
    }

    override fun deleteCity(city: City) = cacheDataSource.deleteCity(city)
}