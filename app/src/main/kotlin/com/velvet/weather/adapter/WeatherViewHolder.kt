package com.velvet.weather.adapter

import android.view.View
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.velvet.weather.R

class WeatherViewHolder(view: View) : GenericViewHolder<ItemUi>(view) {

    override fun bind(item: ItemUi) = with(itemView) {
        item.show(
            findViewById(R.id.city_name),
            findViewById(R.id.temperature),
            findViewById(R.id.delete)
        )
    }
}