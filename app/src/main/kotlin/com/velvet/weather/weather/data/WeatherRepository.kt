package com.velvet.weather.weather.data

import com.velvet.weather.weather.data.schemas.City
import com.velvet.weather.weather.domain.WeatherDomain
import com.velvet.weather.weather.presentation.AddCity
import com.velvet.weather.weather.presentation.DeleteCity

interface WeatherRepository : AddCity, DeleteCity {
    suspend fun updateWeather(): WeatherDomain

    suspend fun getSaved(): WeatherDomain
}