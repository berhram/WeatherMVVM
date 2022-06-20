package com.velvet.weather.weather.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.GenericAdapter
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class BaseAdapter : GenericAdapter.Base(
    ViewHolderFactoryChain.Exception()
)
