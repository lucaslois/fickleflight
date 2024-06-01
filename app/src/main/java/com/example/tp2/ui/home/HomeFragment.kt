package com.example.tp2.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.R
import com.example.tp2.adapters.TrendingDestinationListAdapter
import com.example.tp2.data.network.flights.FlightService
import com.example.tp2.data.network.flights.TrendingDestinationsApiClient
import com.example.tp2.data.network.flights.models.TrendingDestination
import kotlinx.coroutines.CoroutineScope
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TrendingDestinationListAdapter(requireContext(), mutableListOf())


        val flightsService = FlightService()

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    flightsService.getTrendingDestinations()
                }
                response.data?.let { data ->
                    withContext(Dispatchers.Main) {
                        adapter.updateTrendingDestinations(data.toMutableList())
                    }
                }
            } catch (e: Exception) {
                Log.e("FlightService", "Error fetching trending destinations: ${e.message}", e)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.logoToolbar)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)

        (activity as? AppCompatActivity)?.supportActionBar?.title = ""

        val recyclerView = view.findViewById<RecyclerView>(R.id.trendingDestinationsRecyclerView)

        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val profileButton = view.findViewById<ImageView>(R.id.profile_image)
        profileButton.setOnClickListener {

        }

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