package com.example.tp2.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.R
import com.example.tp2.adapters.OfferListAdapter
import com.example.tp2.adapters.TrendingDestinationListAdapter
import com.example.tp2.data.network.flights.FlightService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var adapter: TrendingDestinationListAdapter
    private lateinit var adapterHome: OfferListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TrendingDestinationListAdapter(requireContext(), mutableListOf())
        adapterHome = OfferListAdapter(requireContext(), mutableListOf(), OfferListAdapter.VIEW_TYPE_OFFER_HOME)

        val flightsService = FlightService()
        val offersHome = FlightService()

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    flightsService.getTrendingDestinations()
                }
                response.data.let { data ->
                    withContext(Dispatchers.Main) {
                        adapter.updateTrendingDestinations(data.toMutableList())
                    }
                }

                val responseOffers = withContext(Dispatchers.IO) {
                    offersHome.getActiveOffers()
                }
                responseOffers.data.let { data ->
                    withContext(Dispatchers.Main) {
                        adapterHome.updateOffers(data.toMutableList())
                    }
                }
            } catch (e: Exception) {
                Log.e("FlightService", "Error fetching information: ${e.message}", e)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //val toolbar = view.findViewById<Toolbar>(R.id.customTopToolbar)
        //(activity as? AppCompatActivity)?.setSupportActionBar(toolbar)

        //(activity as? AppCompatActivity)?.supportActionBar?.title = ""

        val recyclerView = view.findViewById<RecyclerView>(R.id.trendingDestinationsRecyclerView)
        val recyclerViewOffersHome = view.findViewById<RecyclerView>(R.id.offersHomeRecyclerView)

        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerViewOffersHome.adapter = adapterHome
        recyclerViewOffersHome.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        //val profileButton = view.findViewById<ImageView>(R.id.profile_image)
        //profileButton.setOnClickListener {
        //
        //}

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}