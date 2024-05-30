package com.example.tp2.ui.searchFlight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.tp2.R

class SearchFlightFragment : Fragment() {
    // TODO: Rename and change types of parameters
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_flight, container, false);
        val btnSearch = view.findViewById<Button>(R.id.btnSearchFlights)
        btnSearch.setOnClickListener {
            findNavController().navigate(R.id.action_searchFlightFragment_to_flightListFragment)
        }
        return view;
    }
}