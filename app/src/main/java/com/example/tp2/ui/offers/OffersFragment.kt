package com.example.tp2.ui.offers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp2.adapters.OfferListAdapter
import com.example.tp2.data.network.flights.FlightService
import com.example.tp2.databinding.FragmentOffersBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OffersFragment : Fragment() {

    private lateinit var adapter: OfferListAdapter
    private var _binding: FragmentOffersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = OfferListAdapter(requireContext(), mutableListOf())

        // Este flightsService se podría cambiar por offers
        val flightsService = FlightService()

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    flightsService.getActiveOffers() // Obtengo las offers para el recycler view
                }
                response.data?.let { data ->
                    withContext(Dispatchers.Main) {
                        adapter.updateOffers(data.toMutableList())
                    }
                }
            } catch (e: Exception) {
                Log.e("OffersFragment", "Error fetching offers: ${e.message}")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOffersBinding.inflate(inflater, container, false)

        // Conecto con el recycler view y la vista
        binding.offersDetailsRecyclerView.adapter = adapter
        binding.offersDetailsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
