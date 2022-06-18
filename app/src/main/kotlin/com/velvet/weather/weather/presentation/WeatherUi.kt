package com.velvet.weather.weather.presentation

import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface WeatherUi : Mapper.Unit<Mapper.Unit<List<ItemUi>>> {

    class Base(private val list: List<ItemUi>) : WeatherUi {
        override fun map(data: Mapper.Unit<List<ItemUi>>) = data.map(list)
    }
}
