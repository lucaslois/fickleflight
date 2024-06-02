package com.example.tp2.ui.searchFlight

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.R
import com.example.tp2.adapters.OfferListAdapter
import com.example.tp2.adapters.TrendingDestinationListAdapter
import com.example.tp2.data.network.flights.FlightService
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFlightFragment : Fragment() {
    private lateinit var adapter: OfferListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = OfferListAdapter(requireContext(), mutableListOf())

        val flightsService = FlightService()

        lifecycleScope.launch {
            try {
//                val response = withContext(Dispatchers.IO) {
//                    flightsService.getActiveOffers()
//                }
//                response.data.let { data ->
//                    withContext(Dispatchers.Main) {
//                        adapter.updateOffers(data.toMutableList())
//                    }
//                }
            } catch (e: Exception) {
                Log.e("FlightService", "Error fetching offers: ${e.message}", e)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_flight, container, false);
        val recyclerView = view.findViewById<RecyclerView>(R.id.offersRecyclerView)

        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val btnSearch = view.findViewById<Button>(R.id.btnSearchFlights)
        btnSearch.setOnClickListener {
            findNavController().navigate(R.id.action_searchFlightFragment_to_flightListFragment)
        }

        return view;
    }
}