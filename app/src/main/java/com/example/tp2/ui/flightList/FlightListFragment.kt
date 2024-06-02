package com.example.tp2.ui.flightList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.R
import com.example.tp2.adapters.FlightListAdapter
import com.example.tp2.data.network.flights.FlightService
import com.example.tp2.data.network.flights.models.BestFlight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlightListFragment : Fragment() {
    private val flightsList: MutableList<BestFlight> = mutableListOf()
    private lateinit var adapter: FlightListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FlightListAdapter(flightsList)

        val flightsService = FlightService()

        CoroutineScope(Dispatchers.Main).launch {
            val response = flightsService.getFlights()
            adapter.updateFlights(response.best_flights.toMutableList())
            updateResultsText(adapter.itemCount)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_flight_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.flightListRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }
    private fun updateResultsText(resultsCount: Int) {
        view?.findViewById<TextView>(R.id.flightResultsTextView)?.text = "$resultsCount results found"
    }
}