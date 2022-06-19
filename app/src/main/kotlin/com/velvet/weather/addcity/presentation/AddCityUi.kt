package com.velvet.weather.addcity.presentation

import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface AddCityUi : Mapper.Unit<Mapper.Unit<List<ItemUi>>> {

    class Base(private val list: List<ItemUi>) : AddCityUi {
        override fun map(data: Mapper.Unit<List<ItemUi>>) = data.map(list)
    }
}