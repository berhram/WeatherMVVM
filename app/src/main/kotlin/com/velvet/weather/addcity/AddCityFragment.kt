package com.velvet.weather.addcity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.velvet.weather.R
import com.velvet.weather.addcity.presentation.AddCityUi
import com.velvet.weather.weather.presentation.BaseAdapter

class AddCityFragment : BackPress.Fragment<AddCityUi, AddCityViewModel>() {

    override val layoutResId: Int = R.layout.fragment_add_city

    override fun viewModelClass(): Class<AddCityViewModel> = AddCityViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.suggested)
        val baseAdapter = BaseAdapter()
        recyclerView.adapter = baseAdapter

        val searchView = view.findViewById<EditText>(R.id.search)
        viewModel.search.observe(viewLifecycleOwner) {
            searchView.setText(it)
        }
        searchView.addTextChangedListener {
            viewModel.input(it.toString())
        }

        val searchButton = view.findViewById<Button>(R.id.search_button)
        searchButton.setOnClickListener {
            viewModel.searchLocations()
        }

        viewModel.observe(this) { ui ->
            ui.map(baseAdapter)
        }
    }
}