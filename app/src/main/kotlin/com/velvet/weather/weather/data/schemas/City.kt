package com.velvet.weather.weather.data.schemas

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double
)