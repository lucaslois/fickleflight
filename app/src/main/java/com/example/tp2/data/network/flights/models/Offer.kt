package com.example.tp2.data.network.flights.models

data class Offer(
    val offer_type_id: String,
    val offer_type_desc: String,
    val card_provider: String,
    val card_types: List<String>,
    val discount_desc_short: String,
    val discount_desc_long: String,
    val tittle_discuount: String,
    val discount_percentage: String,
    val image_url: String
)