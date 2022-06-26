package com.velvet.weather.di

import androidx.lifecycle.ViewModel
import com.github.johnnysc.coremvvm.data.PreferenceDataStore
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.Module
import com.velvet.weather.addcity.presentation.AddCityViewModel
import com.velvet.weather.weather.data.SavedCities
import com.velvet.weather.weather.data.WeatherCacheDataSource
import com.velvet.weather.weather.presentation.WeatherViewModel

class FeaturesDependencyContainer(
    private val coreModule: CoreModule,
    private val dependencyContainer: DependencyContainer,
) : DependencyContainer {

    private val cacheDataSource = WeatherCacheDataSource.Base(
        SavedCities.Base(
            PreferenceDataStore.Base(coreModule.sharedPreferences(PREFS_KEY))
        )
    )

    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when (clazz) {
        AddCityViewModel::class.java -> AddCityModule(
            coreModule = coreModule,
            cacheDataSource = cacheDataSource
        )
        WeatherViewModel::class.java -> WeatherModule(
            coreModule = coreModule,
            cacheDataSource = cacheDataSource,
        )
        else -> dependencyContainer.module(clazz)
    }

    companion object {
        private const val PREFS_KEY = "saved cities"
    }
}