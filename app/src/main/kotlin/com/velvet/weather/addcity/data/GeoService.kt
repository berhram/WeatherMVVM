package com.velvet.weather.addcity.data

import com.velvet.weather.weather.data.schemas.City
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoService {

    @GET("direct?")
    suspend fun findCities(
        @Query("appid") appId: String,
        @Query("q") keyword: String
    ) : List<City>
}