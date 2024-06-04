package com.example.tp2.holders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp2.databinding.ItemOfferDetailBinding

class OfferDetailHolder(private val binding: ItemOfferDetailBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setDiscountDetail(discount: String) {
        binding.offerTypeDetail.text = discount
    }

    fun setCardImageDetail(imageUrl: String) {
        Glide.with(binding.root)
            .load(imageUrl)
            .fitCenter()
            .into(binding.cardLogoDetail)
    }
}
