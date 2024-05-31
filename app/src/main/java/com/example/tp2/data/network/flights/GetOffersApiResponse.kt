package com.example.tp2.data.network.flights

import com.example.tp2.data.network.flights.models.Offer

data class GetOffersApiResponse(
        val data: List<Offer>
)