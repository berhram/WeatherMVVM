package com.velvet.weather.weather.presentation

import com.velvet.weather.weather.data.schemas.City

interface GetSavedCities {

    fun getCities(): List<City>
}