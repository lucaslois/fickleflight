package com.example.tp2.data.network.flights.models

data class CarbonEmissions(
    val difference_percent: Int,
    val this_flight: Int,
    val typical_for_this_route: Int
)