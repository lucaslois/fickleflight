package com.example.tp2.data.network.flights

import com.example.tp2.GetDetailsApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface TrendingDestinationsApiClient {
    @GET("/")
    suspend fun getTrendingDestinations(): Response<GetTrendingDestinationsApiResponse>

    @GET("/flights/1")
    suspend fun getDetails(): Response<GetDetailsApiResponse>
}