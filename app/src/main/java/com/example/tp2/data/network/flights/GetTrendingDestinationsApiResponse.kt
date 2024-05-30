package com.example.tp2.data.network.flights

import com.example.tp2.data.network.flights.models.TrendingDestination

data class GetTrendingDestinationsApiResponse(
    val data: List<TrendingDestination>
)