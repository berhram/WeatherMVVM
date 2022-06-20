package com.velvet.weather.adapter

import com.github.johnnysc.coremvvm.presentation.adapter.GenericAdapter
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain
import com.velvet.weather.adapter.LastUpdateViewHolderChain
import com.velvet.weather.adapter.WeatherViewHolderFactoryChain

interface ItemAdapter {
    class Weather :
        GenericAdapter.Base(
            WeatherViewHolderFactoryChain(
                LastUpdateViewHolderChain(
                    ViewHolderFactoryChain.Exception()
                )
            )
        )

    class AddCity : GenericAdapter.Base(
        LocationViewHolderFactoryChain(
            ViewHolderFactoryChain.Exception()
        )
    )
}
