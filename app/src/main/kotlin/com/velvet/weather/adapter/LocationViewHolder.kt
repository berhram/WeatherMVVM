package com.velvet.weather.adapter

import android.view.View
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.velvet.weather.R

class LocationViewHolder(view: View) : GenericViewHolder<ItemUi>(view) {

    override fun bind(item: ItemUi) = with(itemView) {
        item.show(
            findViewById(R.id.city_search_name),
            findViewById(R.id.add_to_saved)
        )
    }
}