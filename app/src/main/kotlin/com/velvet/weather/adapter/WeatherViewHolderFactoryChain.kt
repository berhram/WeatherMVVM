package com.velvet.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain
import com.velvet.weather.R
import com.velvet.weather.weather.presentation.UiTypes

class WeatherViewHolderFactoryChain(
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : ViewHolderFactoryChain<ItemUi> {

    override fun viewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<ItemUi> =
        if (viewType == UiTypes.CITY.value)
            WeatherViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_weather, parent, false)
            )
        else viewHolderFactoryChain.viewHolder(parent, viewType)
}