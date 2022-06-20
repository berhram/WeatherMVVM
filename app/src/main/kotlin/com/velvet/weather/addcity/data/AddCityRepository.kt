package com.velvet.weather.addcity.data

import com.velvet.weather.addcity.domain.AddCityDomain
import com.velvet.weather.weather.presentation.AddCity

interface AddCityRepository : AddCity {

    suspend fun getSuggestions(keyword: String): AddCityDomain
}