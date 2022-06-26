package com.velvet.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain
import com.velvet.weather.R
import com.velvet.weather.presentation.UiTypes

class LocationViewHolderFactoryChain(
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : ViewHolderFactoryChain<ItemUi> {

    override fun viewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<ItemUi> =
        if (viewType == UiTypes.LOCATION.value)
            LocationViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_city, parent, false)
            )
        else viewHolderFactoryChain.viewHolder(parent, viewType)
}