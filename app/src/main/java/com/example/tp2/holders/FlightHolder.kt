package com.example.tp2.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

    fun setDestination(destination: String) {
        val text = view.findViewById<TextView>(R.id.txtDestination)
        text.text = destination
    }
}