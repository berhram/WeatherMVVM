package com.velvet.weather.weather.presentation

import com.github.johnnysc.coremvvm.presentation.Communication

interface WeatherCommunication : Communication.Mutable<WeatherUi> {
    class Base : Communication.UiUpdate<WeatherUi>(), WeatherCommunication
}