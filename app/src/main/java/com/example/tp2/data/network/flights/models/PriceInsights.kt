package com.example.tp2.data.network.flights.models

data class PriceInsights(
    val lowest_price: Int,
    val price_history: List<List<Int>>,
    val price_level: String,
    val typical_price_range: List<Int>
)