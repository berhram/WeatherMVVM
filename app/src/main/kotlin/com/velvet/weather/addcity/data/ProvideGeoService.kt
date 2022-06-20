package com.velvet.weather.addcity.data

import com.github.johnnysc.coremvvm.data.MakeService
import com.github.johnnysc.coremvvm.data.ProvideRetrofitBuilder

interface ProvideGeoService {

    fun getService() : GeoService

    class Base(
        retrofitBuilder: ProvideRetrofitBuilder
    ) : MakeService.Abstract(
        retrofitBuilder
    ), ProvideGeoService {

        override fun getService(): GeoService = service(GeoService::class.java)

        override fun baseUrl(): String = "https://api.openweathermap.org/geo/1.0/"
    }
}