package com.example.tp2.data.network.flights

import retrofit2.Response
import retrofit2.http.GET

interface TrendingDestinationsApiClient {
    @GET("/")
    suspend fun getTrendingDestinations(): Response<GetTrendingDestinationsApiResponse>
}