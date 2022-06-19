package com.velvet.weather.weather.data

import android.annotation.SuppressLint
import com.velvet.weather.weather.data.schemas.City
import com.velvet.weather.weather.domain.WeatherDomain
import java.text.SimpleDateFormat
import java.util.*

class BaseWeatherRepository(
    private val cacheDataSource: WeatherCacheDataSource,
    private val cloudDataSource: WeatherCloudDataSource,
) : WeatherRepository {

    @SuppressLint("SimpleDateFormat")
    override suspend fun updateWeather(): WeatherDomain {
        var date: Long? = null
        val output = mutableListOf<Pair<City, Double>>()
        cacheDataSource.getCities().forEach {
            val result = cloudDataSource.getWeather(it)
            date = result.currentWeather.time
            output.add(it to result.currentWeather.temp)
        }
        return WeatherDomain.Base(
            date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
                Date(
                    date ?: System.currentTimeMillis()
                )
            ),
            list = output
        )
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

    //override fun addCity(city: City) = cacheDataSource.addCity(city)

    override fun deleteCity(city: City) = cacheDataSource.deleteCity(city)
}