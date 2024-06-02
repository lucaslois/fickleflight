package com.example.tp2.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.R
import com.example.tp2.adapters.ImageDetailAdapter
import com.example.tp2.data.network.flights.FlightService
import kotlinx.coroutines.launch


class DetailsFragment : Fragment() {

    private lateinit var adapter: ImageDetailAdapter
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ImageDetailAdapter(mutableListOf())

        val flightService = FlightService()
        val flightId = args.destination

        lifecycleScope.launch {
            try {
                val response = flightService.getDetails(flightId)

                val details = response.data
                view?.let {
                    it.findViewById<TextView>(R.id.txtDetailDestination).text = details.destination
                    it.findViewById<TextView>(R.id.txtDetailCountry).text = details.country
                    it.findViewById<TextView>(R.id.txtDetailDuration).text = details.duration
                    it.findViewById<TextView>(R.id.txtPrice).text = details.price
                    it.findViewById<TextView>(R.id.txtDetailDescription).text = details.description
                }

                adapter.updateImages(details.images)
            } catch (e: Exception) {
                // Manejar cualquier excepción aquí
                Log.e("DetailsFragment", "Error: ${e.message}")
                throw e
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.DetailsDestination)

        // Configurar el RecyclerView con el adaptador
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        return view

    }


}