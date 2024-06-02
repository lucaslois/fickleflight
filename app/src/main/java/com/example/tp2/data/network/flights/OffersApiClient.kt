package com.example.tp2.data.network.flights

import retrofit2.Response
import retrofit2.http.GET

interface OffersApiClient {
    @GET("/offers")
    suspend fun getActiveOffers(): Response<GetOffersApiResponse>
}