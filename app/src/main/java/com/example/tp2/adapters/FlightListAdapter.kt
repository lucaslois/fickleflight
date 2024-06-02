package com.example.tp2.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.R
import com.example.tp2.data.network.flights.models.BestFlight
import com.example.tp2.data.network.flights.models.Flight
import com.example.tp2.databinding.ItemFlightBinding
import com.example.tp2.holders.FlightHolder
import com.example.tp2.ui.flightList.FlightDetailClickable

class FlightListAdapter(
    private val flightList: MutableList<BestFlight>,
    private val onFlightDetailClickable: FlightDetailClickable
) : RecyclerView.Adapter<FlightHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFlightBinding.inflate(inflater, parent, false)
        return FlightHolder(binding)
    }

    override fun getItemCount(): Int = flightList.size

    override fun onBindViewHolder(holder: FlightHolder, position: Int) {
        val flight = flightList[position]
        holder.setOrigin(flight.flights[0].departure_airport.name)
        holder.setDestination(flight.flights[0].arrival_airport.name)
        holder.setAirplaneLogo(flight.flights[0].airline_logo)
        holder.setOriginId(flight.flights[0].departure_airport.id)
        holder.setDestinationId(flight.flights[0].arrival_airport.id)
        holder.setDuration(flight.flights[0].duration)
        holder.setTravelClass(flight.flights[0].travel_class)
        holder.setFromPrice(flight.price.toString())
        holder.setOnFlightDetailClickable(
            onFlightDetailClickable,
            flight.flights[0].arrival_airport.id
        )
    }

    fun updateFlights(flights: MutableList<BestFlight>) {
        flightList.clear()
        flightList.addAll(flights)
        notifyDataSetChanged()
    }

}