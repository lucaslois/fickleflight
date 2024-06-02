package com.example.tp2.ui.flightList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.R
import com.example.tp2.adapters.FlightListAdapter
import com.example.tp2.data.network.flights.FlightService
import com.example.tp2.data.network.flights.models.BestFlight
import com.example.tp2.databinding.FragmentFlightListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlightListFragment : Fragment(), FlightDetailClickable {
    private lateinit var adapter: FlightListAdapter
    private lateinit var bindings: FragmentFlightListBinding
    private val flightsList: MutableList<BestFlight> = mutableListOf()
    private val viewModel: FlightListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getBestFlights()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = FragmentFlightListBinding.inflate(inflater, container, false)

        setupRecyclerView()
        observeBestFlights()

        return bindings.root
    }

    private fun updateResultsText(resultsCount: Int) {
        view?.findViewById<TextView>(R.id.flightResultsTextView)?.text = "$resultsCount results found"
    }

    override fun onClickFlightDetail(flightId: String) {
        val action =
            FlightListFragmentDirections.actionFlightListFragmentToDetailsFragment(flightId)
        view?.findNavController()?.navigate(action)
    }

    fun setupRecyclerView() {
        adapter = FlightListAdapter(flightsList, this)
        bindings.flightListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        bindings.flightListRecyclerView.adapter = adapter
    }

    fun observeBestFlights() {
        viewModel.bestFlights.observe(viewLifecycleOwner) {bestFlights ->
            bestFlights.let {
                adapter.updateFlights(bestFlights)
                updateResultsText(adapter.itemCount)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            bindings.loader.root.visibility = if(loading) View.VISIBLE else View.GONE
        }
    }
}