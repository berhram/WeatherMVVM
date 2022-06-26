package com.velvet.weather.adapter

import com.github.johnnysc.coremvvm.presentation.adapter.GenericAdapter
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

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
