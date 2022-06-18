package com.velvet.weather.weather.data.schemas

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("current") val currentWeather: CurrentWeather,
)