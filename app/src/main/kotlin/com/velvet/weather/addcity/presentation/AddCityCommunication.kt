package com.velvet.weather.addcity.presentation

import com.github.johnnysc.coremvvm.presentation.Communication

interface AddCityCommunication : Communication.Mutable<AddCityUi> {
    class Base : Communication.UiUpdate<AddCityUi>(), AddCityCommunication
}