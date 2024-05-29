package com.example.tp2.data.network.flights.models

data class OtherFlight(
    val airline_logo: String,
    val carbon_emissions: CarbonEmissions,
    val departure_token: String,
    val flights: List<Flight>,
    val layovers: List<Layover>,
    val price: Int,
    val total_duration: Int,
    val type: String
)