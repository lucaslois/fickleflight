package com.example.tp2.data.network.flights

import com.example.tp2.data.network.flights.models.BestFlight
import com.example.tp2.data.network.flights.models.OtherFlight
import com.example.tp2.data.network.flights.models.PriceInsights
import com.example.tp2.data.network.flights.models.SearchMetadata
import com.example.tp2.data.network.flights.models.SearchParameters

data class GetFlightsApiResponse(
    val best_flights: List<BestFlight>,
    val other_flights: List<OtherFlight>,
    val price_insights: PriceInsights,
    val search_metadata: SearchMetadata,
    val search_parameters: SearchParameters
)