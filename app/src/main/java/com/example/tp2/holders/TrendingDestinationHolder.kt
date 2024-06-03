package com.example.tp2.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.R

class TrendingDestinationHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View

    init {
        this.view = v
    }

    fun setName(name: String) {
        val text = view.findViewById<TextView>(R.id.txtDestinationName)
        text.text = name
    }

    fun setCountry(country: String) {
        val text = view.findViewById<TextView>(R.id.txtDestinationCountry)
        text.text = country
    }

    fun setDuration(duration: String) {
        val text = view.findViewById<TextView>(R.id.txtDestinationDuration)
        text.text = duration
    }

    fun imageView(): ImageView {
        return view.findViewById(R.id.imgDestination)
    }
}