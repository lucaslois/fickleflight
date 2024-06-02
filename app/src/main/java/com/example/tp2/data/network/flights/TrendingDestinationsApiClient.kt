package com.example.tp2.data.network.flights

import com.example.tp2.GetDetailsApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface TrendingDestinationsApiClient {
    @GET("bestFlights.json")
    suspend fun getTrendingDestinations(): Response<GetTrendingDestinationsApiResponse>

    @GET("flightDetail.json")
    suspend fun getDetails(): Response<GetDetailsApiResponse>
}