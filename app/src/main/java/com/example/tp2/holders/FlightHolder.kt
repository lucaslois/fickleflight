package com.example.tp2.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp2.R

class FlightHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View

    init {
        this.view = v
    }

    fun setOrigin(origin: String) {
        val text = view.findViewById<TextView>(R.id.txtOrigin)
        text.text = origin
    }

    fun setOriginId(originId: String) {
        val text = view.findViewById<TextView>(R.id.idOrigin)
        text.text = originId
    }

    fun setDestination(destination: String) {
        val text = view.findViewById<TextView>(R.id.txtDestination)
        text.text = destination
    }

    fun setDestinationId(destinationId: String) {
        val text = view.findViewById<TextView>(R.id.idDestination)
        text.text = destinationId
    }

    fun setDuration(duration: Int) {
        val text = view.findViewById<TextView>(R.id.flightDuration)
        text.text = convertMinutesToDuration(duration)
    }

    fun setAirplaneLogo(imageUrl: String){
        val imageView: ImageView = view.findViewById(R.id.airplaneLogo)
        Glide.with(view.context)
            .load(imageUrl)
            .fitCenter()
            .into(imageView)
    }

    fun setTravelClass(travClass: String) {
        val text = view.findViewById<TextView>(R.id.travelClassText)
        text.text = travClass
    }

    fun setFromPrice(price: String) {
        val text = view.findViewById<TextView>(R.id.fromPrice)
        "$$price".also { text.text = it }
    }
    private fun convertMinutesToDuration(minutes: Int): String {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60
        return String.format("%02d hr %02d min", hours, remainingMinutes)
    }
}