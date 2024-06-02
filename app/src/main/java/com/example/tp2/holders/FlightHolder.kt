package com.example.tp2.holders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp2.R
import com.example.tp2.databinding.ItemFlightBinding
import com.example.tp2.ui.flightList.FlightDetailClickable

class FlightHolder(private val binding: ItemFlightBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setOrigin(origin: String) {
        binding.txtOrigin.text = origin
    }

    fun setOriginId(originId: String) {
        binding.idOrigin.text = originId
    }

    fun setDestination(destination: String) {
        binding.txtDestination.text = destination
    }

    fun setDestinationId(destinationId: String) {
        binding.idDestination.text = destinationId
    }

    fun setDuration(duration: Int) {
        binding.flightDuration.text = convertMinutesToDuration(duration)
    }

    fun setAirplaneLogo(imageUrl: String) {
        Glide.with(binding.root)
            .load(imageUrl)
            .fitCenter()
            .into(binding.airplaneImg)
    }

    fun setTravelClass(travClass: String) {
        binding.travelClass.text = travClass
    }

    fun setFromPrice(price: String) {
        binding.fromPrice.text = "$$price"
    }

    fun setOnFlightDetailClickable(flightDetailClickable: FlightDetailClickable, flightId: String) {
        binding.buttonViewDetails.setOnClickListener {
            flightDetailClickable.onClickFlightDetail(flightId)

        }

    }

    private fun convertMinutesToDuration(minutes: Int): String {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60
        return String.format("%02d hr %02d min", hours, remainingMinutes)
    }
}