package com.example.tp2.data.network.flights

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlightService {
    var retroFitClient: FlightApiClient
    var auxiliarRetroFitClient: AuxiliarApiClient

    val BASE_URL_FLIGHTS: String = "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/"
    val BASE_URL_API_INTERNAL: String = "https://raw.githubusercontent.com/lucaslois/mock/main/"

    init {
        retroFitClient = Retrofit.Builder()
            .baseUrl(BASE_URL_FLIGHTS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlightApiClient::class.java)

        auxiliarRetroFitClient = Retrofit.Builder()
            .baseUrl(BASE_URL_API_INTERNAL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuxiliarApiClient::class.java)
    }

    suspend fun getFlights(): GetFlightsApiResponse {
        return withContext(Dispatchers.IO) {
            retroFitClient.getFlights().body()!!;
        }
    }

    suspend fun getTrendingDestinations(): GetTrendingDestinationsApiResponse {
        return withContext(Dispatchers.IO) {
            auxiliarRetroFitClient.getTrendingDestinations().body()!!;
        }
    }

    /**
     * destination Parameter is ignored due to the endpoint is hardcoded.
     * FlightDetails endpoint should receive a destination id to show the details info for that destination
     */
    suspend fun getDetails(destination: String): GetDetailsApiResponse {
        return withContext(Dispatchers.IO) {
            auxiliarRetroFitClient.getDetails().body()!!;
        }
    }

    suspend fun getActiveOffers(): GetOffersApiResponse {
        return withContext(Dispatchers.IO) {
            auxiliarRetroFitClient.getActiveOffers().body()!!
        }
    }
}