package com.example.tp2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp2.R
import com.example.tp2.data.network.flights.models.Flight
import com.example.tp2.data.network.flights.models.TrendingDestination
import com.example.tp2.holders.TrendingDestinationHolder

class TrendingDestinationListAdapter(
    private val context: Context,
    private val trendingDestinations: MutableList<TrendingDestination>
) :
    RecyclerView.Adapter<TrendingDestinationHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingDestinationHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trending_destination, parent, false)
        return TrendingDestinationHolder(view)
    }

    override fun getItemCount(): Int = trendingDestinations.size

    override fun onBindViewHolder(holder: TrendingDestinationHolder, position: Int) {
        val trendingDestination = trendingDestinations[position]

        holder.setName(trendingDestination.name)
        holder.setCountry(trendingDestination.country)
        holder.setDuration(trendingDestination.duration)

        Glide.with(context)
            .load(trendingDestination.imageUrl)
            .into(holder.imageView())
    }

    fun updateTrendingDestinations(trendingDestinationList: List<TrendingDestination>) {
        trendingDestinations.clear()
        trendingDestinations.addAll(trendingDestinationList.toMutableList())
        notifyDataSetChanged()
    }

}