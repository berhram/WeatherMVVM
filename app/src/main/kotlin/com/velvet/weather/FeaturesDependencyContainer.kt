package com.velvet.weather

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.Module
import com.velvet.weather.addcity.AddCityModule
import com.velvet.weather.addcity.presentation.AddCityViewModel
import com.velvet.weather.weather.data.SavedCities
import com.velvet.weather.weather.data.WeatherCacheDataSource
import com.velvet.weather.weather.presentation.WeatherViewModel

class FeaturesDependencyContainer(
    context: Context,
    private val coreModule: CoreModule,
    private val dependencyContainer: DependencyContainer,
    private val navigationCommunication: NavigationCommunication.Update
) : DependencyContainer {

    private val cacheDataSource = WeatherCacheDataSource.Base(
        SavedCities.Base(
            Room.databaseBuilder(context, AppDatabase::class.java, "weather-app-database")
                .build().cityDao()
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
            navigationCommunication
        )
        else -> dependencyContainer.module(clazz)
    }
}