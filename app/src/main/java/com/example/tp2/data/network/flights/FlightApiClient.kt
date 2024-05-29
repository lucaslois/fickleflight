package com.example.tp2.data.network.flights

import retrofit2.Response
import retrofit2.http.GET

interface FlightApiClient {
    @GET("/search?engine=google_flights&api_key=123&departure_id=EZE&arrival_id=MIA&outbound_date=2024-05-31&return_date=2024-06-10")
    suspend fun getFlights(): Response<GetFlightsApiResponse>
}