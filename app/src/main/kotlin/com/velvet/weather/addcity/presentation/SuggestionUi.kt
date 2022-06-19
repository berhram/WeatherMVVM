package com.velvet.weather.addcity.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView
import com.velvet.weather.weather.data.schemas.City
import com.velvet.weather.weather.presentation.AddCity
import com.velvet.weather.weather.presentation.UiTypes

class SuggestionUi(
    private val location: String,
    private val city: City,
    private val addCity: AddCity
) : ItemUi {

    override fun content(): String = location

    override fun id(): String = location

    override fun show(vararg views: MyView) {
        views[0].show(location)
        views[0].handleClick {
            addCity.addCity(city)
        }
    }

    override fun type(): Int = UiTypes.LOCATION.value
}