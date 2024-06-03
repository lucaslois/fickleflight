package com.example.tp2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.R
import com.example.tp2.data.network.flights.models.Offer
import com.example.tp2.databinding.ItemFlightBinding
import com.example.tp2.databinding.ItemOfferBinding
import com.example.tp2.holders.OfferHolder

class OfferListAdapter(
    private val context: Context,
    private val offerList: MutableList<Offer>
) : RecyclerView.Adapter<OfferHolder>() {


    override fun onBindViewHolder(holder: OfferHolder, position: Int) {
        val offer = offerList[position]
        holder.setDiscount(offer.discount_desc_short)
        holder.setOfferType(offer.offer_type_desc)
        holder.setCardImage(offer.image_url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOfferBinding.inflate(inflater, parent, false)
        return OfferHolder(binding)
    }

    override fun getItemCount(): Int = offerList.size

    fun updateOffers(offers: MutableList<Offer>) {
        offerList.clear()
        offerList.addAll(offers)
        notifyDataSetChanged()
    }
}