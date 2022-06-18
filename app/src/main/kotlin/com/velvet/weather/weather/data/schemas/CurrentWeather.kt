package com.velvet.weather.weather.data.schemas

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("dt") val time: Long,
    @SerializedName("temp") val temp: Double
)