package com.example.tp2.ui.offers

import OfferListAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.tp2.data.network.flights.FlightService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.tp2.R

class OffersFragment : Fragment() {

    private lateinit var adapter: OfferListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = OfferListAdapter(requireContext(), mutableListOf(), OfferListAdapter.VIEW_TYPE_OFFER_DETAIL)

        val offersDetails = FlightService()

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    offersDetails.getActiveOffers()
                }
                withContext(Dispatchers.Main) {
                    if (response.data.isNotEmpty()) {
                        adapter.updateOffers(response.data.toMutableList())

                    } else {
                        Toast.makeText(requireContext(), "No hay ofertas disponibles", Toast.LENGTH_SHORT).show()
                        Log.e("OffersFragment", "No offers available")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error fetching offers: ${e.message}", Toast.LENGTH_SHORT).show()
                    Log.e("OffersFragment", "Error fetching offers: ${e.message}")
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_offers, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.offersDetailsRecyclerView)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }
}

