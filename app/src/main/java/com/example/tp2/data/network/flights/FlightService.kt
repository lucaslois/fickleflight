package com.example.tp2.data.network.flights

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class FlightService {
    val BASE_URL_FLIGHTS: String = "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/"
    val BASE_URL_TRENDING_DESTINATIONS: String = "https://ed84cd0e-2bad-4646-bab3-9658c69065d8.mock.pstmn.io/"
    val BASE_URL_OFFERS: String ="https://408a9dba-178e-4ede-8f7d-a403b3421abf.mock.pstmn.io"
    suspend fun getFlights(): GetFlightsApiResponse {
        return withContext(Dispatchers.IO) {
            val retroFitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL_FLIGHTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val retrofit = retroFitBuilder.create(FlightApiClient::class.java)

            retrofit.getFlights().body()!!;
        }
    }

    suspend fun getTrendingDestinations(): GetTrendingDestinationsApiResponse {
        return withContext(Dispatchers.IO) {
            val retroFitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL_TRENDING_DESTINATIONS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val retrofit = retroFitBuilder.create(TrendingDestinationsApiClient::class.java)

            retrofit.getTrendingDestinations().body()!!;
        }
    }

    suspend fun getActiveOffers(): GetOffersApiResponse {
        return withContext(Dispatchers.IO) {
            val retroFitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL_OFFERS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val retrofit = retroFitBuilder.create(OffersApiClient::class.java)

            retrofit.getActiveOffers().body()!!;
        }
    }
}