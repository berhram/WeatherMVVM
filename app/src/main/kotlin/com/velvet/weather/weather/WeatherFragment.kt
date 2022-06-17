package com.velvet.weather.weather

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.velvet.weather.R

class WeatherFragment : BackPress.Fragment<WeatherUi, WeatherViewModel>() {
    override fun viewModelClass() = WeatherViewModel::class.java

    override val layoutResId: Int = R.layout.fragment_weather

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.overview)
        val weatherAdapter = WeatherAdapter()
        recyclerView.adapter = weatherAdapter

        viewModel.observe(this) { ui ->
            ui.map(weatherAdapter)
        }
    }
}
