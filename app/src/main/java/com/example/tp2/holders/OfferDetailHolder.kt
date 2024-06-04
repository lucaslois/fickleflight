package com.example.tp2.holders

import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp2.databinding.ItemOfferDetailBinding

class OfferDetailHolder(private val binding: ItemOfferDetailBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setDiscountDetail(discount: String) {
        binding.offerTypeDetail.text = discount
    }

    fun setTitleDetail(tittle: String){
        // Poner en negrita "Get 20% discount" y "25% discount"
        var formattedText = tittle.replace("Get 20% discount", "<b>Get 20% discount</b>")
        formattedText = formattedText.replace("25% discount", "<b>25% discount</b>")

        // Asignar el texto formateado al TextView
        binding.offerTitleDetail.text = Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY)
    }

    fun setCardImageDetail(imageUrl: String) {
        Glide.with(binding.root)
            .load(imageUrl)
            .fitCenter()
            .into(binding.cardLogoDetail)
    }
}
