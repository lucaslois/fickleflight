package com.example.tp2.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp2.R

class OfferHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View

    init {
        this.view = v
    }

    fun setDicount(discount: String) {
        val text = view.findViewById<TextView>(R.id.offerTitle)
        text.text = discount
    }

    fun setDescription(origin: String) {
        val text = view.findViewById<TextView>(R.id.offerDescription)
        text.text = origin
    }

    fun setCardImage(imageUrl: String){
        val imageView: ImageView = view.findViewById(R.id.cardLogo)
        Glide.with(view.context)
            .load(imageUrl)
            .fitCenter()
            .into(imageView)
    }
}