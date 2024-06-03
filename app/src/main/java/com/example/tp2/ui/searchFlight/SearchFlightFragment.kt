package com.example.tp2.ui.searchFlight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.R
import com.example.tp2.adapters.OfferListAdapter
import com.example.tp2.ui.details.OffersViewModel

class SearchFlightFragment : Fragment() {
    private lateinit var adapter: OfferListAdapter
    private val viewModel: OffersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = OfferListAdapter(requireContext(), mutableListOf())
        viewModel.getActiveOffers()
        viewModel.activeOffers.observe(
            this,
        ) {
            adapter.updateOffers(it)
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