package com.example.tp2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp2.R
import com.example.tp2.data.network.flights.models.BestFlight
import com.example.tp2.data.network.flights.models.Details
import com.example.tp2.holders.FlightHolder
import com.example.tp2.holders.ImageDetailHolder

class ImageDetailAdapter (

    private val imageList:MutableList<String>
) : RecyclerView.Adapter<ImageDetailHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDetailHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_detail, parent, false)
        return ImageDetailHolder(view)
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: ImageDetailHolder, position: Int) {
        val imageDetail = imageList[position]

        holder.setImage(imageDetail)
    }

    fun updateImages(images: List<String>) {
        imageList.clear()
        imageList.addAll(images)
        notifyDataSetChanged()
    }

}