package com.velvet.weather.weather.presentation

import com.velvet.weather.weather.data.schemas.City

interface DeleteCity {

    fun deleteCity(city: City)
}