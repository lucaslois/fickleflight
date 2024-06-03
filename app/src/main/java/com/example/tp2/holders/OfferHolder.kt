package com.example.tp2.holders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp2.databinding.ItemOfferBinding

class OfferHolder(private val binding: ItemOfferBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setDiscount(discount: String) {
        binding.offerTitle.text = discount
    }

    fun setOfferType(offerType: String) {
        binding.offerType.text = offerType
    }

    fun setCardImage(imageUrl: String){
        Glide.with(binding.root)
            .load(imageUrl)
            .fitCenter()
            .into(binding.cardLogo)
    }
}