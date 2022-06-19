package com.velvet.weather.addcity

import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError
import com.velvet.weather.Settings.API_KEY
import com.velvet.weather.addcity.data.GeoService

interface GeoCloudDataSource {

    suspend fun getSuggestions(keyword: String) : List<City>

    class Base(
        private val geoService: GeoService,
        handleError: HandleError
    ) : CloudDataSource.Abstract(handleError), GeoCloudDataSource {

        override suspend fun getSuggestions(keyword: String): List<City> = handle {
            geoService.findCities(
                appId = API_KEY,
                keyword = keyword
            )
        }
    }
}