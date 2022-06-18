package com.velvet.weather.weather.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.GenericAdapter
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class WeatherAdapter : GenericAdapter.Base(
    ViewHolderFactoryChain.Exception()
)
