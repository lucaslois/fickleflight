package com.example.tp2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.R
import com.example.tp2.data.network.flights.models.BestFlight
import com.example.tp2.data.network.flights.models.Offer
import com.example.tp2.holders.FlightHolder
import com.example.tp2.holders.OfferHolder

class OfferListAdapter(
    private val context: Context,
    private val offerList: MutableList<Offer>
) : RecyclerView.Adapter<OfferHolder>() {


    override fun onBindViewHolder(holder: OfferHolder, position: Int) {
        val offer = offerList[position]
        holder.setDicount(offer.discount)
        holder.setDescription(offer.discount_type)
        holder.setCardImage(offer.imageUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_offer, parent, false)
        return OfferHolder(view)
    }

    override fun getItemCount(): Int = offerList.size

    fun updateOffers(offers: MutableList<Offer>) {
        offerList.clear()
        offerList.addAll(offers)
        notifyDataSetChanged()
    }
}