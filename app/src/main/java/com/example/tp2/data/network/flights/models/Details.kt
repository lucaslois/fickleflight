package com.example.tp2.data.network.flights.models

data class Details(
    val destination: String,
    val duration: String,
    val country: String,
    val price: String,
    val description: String,
    val images: List<String>,

)