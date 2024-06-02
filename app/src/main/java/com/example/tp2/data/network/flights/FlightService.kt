package com.example.tp2.data.network.flights

import com.example.tp2.GetDetailsApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlightService {
    var retroFitClient: FlightApiClient
    var retroFitClientInternal: TrendingDestinationsApiClient

    val BASE_URL_FLIGHTS: String = "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/"
    val BASE_URL_API_INTERNAL: String = "https://raw.githubusercontent.com/lucaslois/mock/main/"
    val BASE_URL_OFFERS: String ="https://408a9dba-178e-4ede-8f7d-a403b3421abf.mock.pstmn.io"

    init {
        retroFitClient = Retrofit.Builder()
            .baseUrl(BASE_URL_FLIGHTS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlightApiClient::class.java)

        retroFitClientInternal = Retrofit.Builder()
            .baseUrl(BASE_URL_API_INTERNAL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TrendingDestinationsApiClient::class.java)
    }

    suspend fun getFlights(): GetFlightsApiResponse {
        return withContext(Dispatchers.IO) {
            retroFitClient.getFlights().body()!!;
        }
    }

    suspend fun getTrendingDestinations(): GetTrendingDestinationsApiResponse {
        return withContext(Dispatchers.IO) {
            retroFitClientInternal.getTrendingDestinations().body()!!;
        }
    }

    /**
     * destination Parameter is ignored due to the endpoint is hardcoded.
     * FlightDetails endpoint should receive a destination id to show the details info for that destination
     */
    suspend fun getDetails(destination: String): GetDetailsApiResponse {
        return withContext(Dispatchers.IO) {
            retroFitClientInternal.getDetails().body()!!;
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