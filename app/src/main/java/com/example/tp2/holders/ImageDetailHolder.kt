package com.example.tp2.holders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp2.R

class ImageDetailHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v

    init {
        this.view = v
    }




    fun setImage(imageUrl:String){
        val imageView: ImageView = view.findViewById(R.id.imgDetail)
        Glide.with(view.context)
            .load(imageUrl)
            .fitCenter()
            .into(imageView)
    }

    fun imageView(): ImageView {
        return view.findViewById(R.id.imgDetail)
    }

}
