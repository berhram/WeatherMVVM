package com.velvet.weather.addcity.data

import com.velvet.weather.addcity.domain.AddCityDomain
import com.velvet.weather.weather.data.WeatherCacheDataSource
import com.velvet.weather.weather.data.schemas.City

class BaseAddCityRepository(
    private val cacheDataSource: WeatherCacheDataSource,
    private val cloudDataSource: GeoCloudDataSource
) : AddCityRepository {

    override fun addCity(city: City) = cacheDataSource.addCity(city)

    override suspend fun getSuggestions(keyword: String) : AddCityDomain {
        return AddCityDomain.Base(cloudDataSource.getSuggestions(keyword))
    }

}