package com.velvet.weather.weather.data

import com.velvet.weather.weather.data.schemas.City
import com.velvet.weather.weather.data.schemas.CurrentWeather
import com.velvet.weather.weather.data.schemas.Forecast
import com.velvet.weather.weather.domain.WeatherDomain
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

internal class BaseWeatherRepositoryTest {

    private val exampleCities = listOf(
        City(
            "Denver",
            "USA",
            31.0,
            46.0
        ),
        City(
            "Quebec",
            "CA",
            66.0,
            89.0
        ),
        City(
            "Ryazan",
            "RU",
            10.0,
            11.0
        )
    )

    private val exampleCitiesWithTemp = listOf(
        City(
            "Denver",
            "USA",
            31.0,
            46.0
        ) to 5.0,
        City(
            "Quebec",
            "CA",
            66.0,
            89.0
        ) to 5.0,
        City(
            "Ryazan",
            "RU",
            10.0,
            11.0
        ) to 5.0
    )

    @Test
    fun updateWeather_whenCacheIsNotEmpty_shouldReturnCachedAndRefreshTempAndTime() =
        runBlocking(Dispatchers.Unconfined) {
            val cloud = mockk<WeatherCloudDataSource>()
            val cache = mockk<WeatherCacheDataSource>()
            val repository = BaseWeatherRepository(cache, cloud)
            every { cache.getCities() } returns exampleCities
            coEvery { cloud.getWeather(any()) } returns Forecast(CurrentWeather(100500L, 5.0))
            assertEquals(WeatherDomain.Base(100500L, exampleCitiesWithTemp), repository.updateWeather())
            coVerify(exactly = 1) { cache.getCities() }
            coVerify(exactly = 3) { cloud.getWeather(any()) }
        }

    @Test
    fun updateWeather_whenCacheIsEmpty_shouldReturnEmptyAndNotRefreshTempAndTime() =
        runBlocking(Dispatchers.Unconfined) {
            val cloud = mockk<WeatherCloudDataSource>()
            val cache = mockk<WeatherCacheDataSource>()
            val repository = BaseWeatherRepository(cache, cloud)
            every { cache.getCities() } returns emptyList()
            coEvery { cloud.getWeather(any()) } returns Forecast(CurrentWeather(0, 0.0))
            assertEquals(WeatherDomain.Base(null, emptyList()), repository.updateWeather())
            coVerify(exactly = 1) { cache.getCities() }
            coVerify(exactly = 0) { cloud.getWeather(any()) }
        }

    @Test
    fun getSaved_whenNothingSaved_shouldReturnNothing() = runBlocking(Dispatchers.Unconfined) {
        val cloud = mockk<WeatherCloudDataSource>()
        val cache = mockk<WeatherCacheDataSource>()
        val repository = BaseWeatherRepository(cache, cloud)
        every { cache.getCities() } returns emptyList()
        assertEquals(WeatherDomain.Base(null, emptyList()), repository.getSaved())
        coVerify(exactly = 1) { cache.getCities() }
    }

    private val exampleCitiesWithoutTemp = listOf(
        City(
            "Denver",
            "USA",
            31.0,
            46.0
        ) to null,
        City(
            "Quebec",
            "CA",
            66.0,
            89.0
        ) to null,
        City(
            "Ryazan",
            "RU",
            10.0,
            11.0
        ) to null
    )

    @Test
    fun getSaved_whenSaved_shouldReturnSaved() = runBlocking(Dispatchers.Unconfined) {
        val cloud = mockk<WeatherCloudDataSource>()
        val cache = mockk<WeatherCacheDataSource>()
        val repository = BaseWeatherRepository(cache, cloud)
        every { cache.getCities() } returns exampleCities
        assertEquals(WeatherDomain.Base(null, exampleCitiesWithoutTemp), repository.getSaved())
        coVerify(exactly = 1) { cache.getCities() }
    }

    @Test
    fun deleteCity() {
        val cloud = mockk<WeatherCloudDataSource>()
        val cache = mockk<WeatherCacheDataSource>()
        val repository = BaseWeatherRepository(cache, cloud)
        every { cache.deleteCity(any()) } returns Unit
        repository.deleteCity(
            City(
                "Denver",
                "USA",
                31.0,
                46.0
            )
        )
        coVerify(exactly = 1) { cache.deleteCity(any()) }
    }
}