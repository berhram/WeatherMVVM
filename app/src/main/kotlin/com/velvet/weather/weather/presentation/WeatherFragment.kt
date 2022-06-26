package com.velvet.weather.weather.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.velvet.weather.R
import com.velvet.weather.adapter.ItemAdapter

class WeatherFragment : BackPress.Fragment<WeatherUi, WeatherViewModel>() {

    override fun viewModelClass() = WeatherViewModel::class.java

    override val layoutResId: Int = R.layout.fragment_weather

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.overview)
        val adapter = ItemAdapter.Weather()
        recyclerView.adapter = adapter

        val refreshButton = view.findViewById<FloatingActionButton>(R.id.refresh)
        refreshButton.setOnClickListener { viewModel.refresh() }

        viewModel.observe(this) { ui ->
            ui.map(adapter)
        }
    }
}
