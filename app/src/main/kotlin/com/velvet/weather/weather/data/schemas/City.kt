package com.velvet.weather.weather.data.schemas

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class City(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
)