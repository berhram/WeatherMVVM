package com.velvet.weather.weather

import com.github.johnnysc.coremvvm.presentation.Communication

interface WeatherCommunication : Communication.Mutable<WeatherUi> {
    class Base: Communication.UiUpdate<WeatherUi>(), WeatherCommunication
}