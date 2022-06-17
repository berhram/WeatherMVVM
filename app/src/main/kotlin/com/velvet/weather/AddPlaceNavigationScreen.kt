package com.velvet.weather

import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.github.johnnysc.coremvvm.presentation.ShowStrategy

class AddPlaceNavigationScreen : NavigationScreen(
    id = "AddPlaceNavigationScreen",
    clasz = AddPlaceFragment::class.java,
    strategy = ShowStrategy.REPLACE
)