package com.example.tp2.holders

import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp2.databinding.ItemOfferBinding

class OfferHolder(private val binding: ItemOfferBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setDiscount(discount: String) {

        var formattedText = discount.replace("20% discount", "<b>20% discount</b>")
        formattedText = formattedText.replace("25% discount", "<b>25% discount</b>")

        binding.offerTitle.text = Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY)
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