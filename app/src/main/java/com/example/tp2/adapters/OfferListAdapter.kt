package com.example.tp2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.data.network.flights.models.Offer
import com.example.tp2.databinding.ItemOfferBinding
import com.example.tp2.databinding.ItemOfferDetailBinding
import com.example.tp2.holders.OfferHolder
import com.example.tp2.holders.OfferDetailHolder

class OfferListAdapter(
    private val context: Context,
    private val offerList: MutableList<Offer>,
    private val viewType: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_OFFER = 0
        const val VIEW_TYPE_OFFER_DETAIL = 1
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_OFFER -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemOfferBinding.inflate(inflater, parent, false)
                OfferHolder(binding)
            }
            VIEW_TYPE_OFFER_DETAIL -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemOfferDetailBinding.inflate(inflater, parent, false)
                OfferDetailHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val offer = offerList[position]
        when (holder) {
            is OfferHolder -> {
                holder.setDiscount(offer.discount_desc_short)
                holder.setOfferType(offer.offer_type_desc)
                holder.setCardImage(offer.image_url)
            }
            is OfferDetailHolder -> {
                holder.setDiscountDetail(offer.discount_desc_long)
                holder.setCardImageDetail(offer.image_url)
            }
        }
    }

    override fun getItemCount(): Int = offerList.size

    fun updateOffers(offers: MutableList<Offer>) {
        offerList.clear()
        offerList.addAll(offers)
        notifyDataSetChanged()
    }
}
