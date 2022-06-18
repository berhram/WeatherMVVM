package com.velvet.weather.weather

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.velvet.weather.R

class WeatherFragment : BackPress.Fragment<WeatherUi, WeatherViewModel>() {

    override fun viewModelClass() = WeatherViewModel::class.java

    override val layoutResId: Int = R.layout.fragment_weather

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.overview)
        val weatherAdapter = WeatherAdapter()
        recyclerView.adapter = weatherAdapter

        val addCityButton = view.findViewById<FloatingActionButton>(R.id.add_city)
        addCityButton.setOnClickListener {  }

        val refreshButton = view.findViewById<FloatingActionButton>(R.id.refresh)
        refreshButton.setOnClickListener { viewModel.refresh() }

        viewModel.observe(this) { ui ->
            ui.map(weatherAdapter)
        }
    }
}
