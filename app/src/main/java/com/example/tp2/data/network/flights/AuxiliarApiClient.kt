package com.example.tp2.data.network.flights

import retrofit2.Response
import retrofit2.http.GET

interface AuxiliarApiClient {
    @GET("bestFlights.json")
    suspend fun getTrendingDestinations(): Response<GetTrendingDestinationsApiResponse>

    @GET("flightDetail.json")
    suspend fun getDetails(): Response<GetDetailsApiResponse>

    @GET("offers.json")
    suspend fun getActiveOffers(): Response<GetOffersApiResponse>
}