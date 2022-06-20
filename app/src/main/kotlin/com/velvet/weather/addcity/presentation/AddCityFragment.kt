package com.velvet.weather.addcity.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.velvet.weather.R
import com.velvet.weather.adapter.ItemAdapter

class AddCityFragment : BackPress.Fragment<AddCityUi, AddCityViewModel>() {

    override val layoutResId: Int = R.layout.fragment_add_city

    override fun viewModelClass(): Class<AddCityViewModel> = AddCityViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.suggested)
        val adapter = ItemAdapter.AddCity()
        recyclerView.adapter = adapter

        val searchView = view.findViewById<EditText>(R.id.search)

        searchView.addTextChangedListener {
            viewModel.input(it.toString())
        }

        val searchButton = view.findViewById<Button>(R.id.search_button)
        searchButton.setOnClickListener {
            viewModel.searchLocations()
        }

        viewModel.observe(this) { ui ->
            ui.map(adapter)
        }
    }
}