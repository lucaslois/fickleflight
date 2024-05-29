package com.example.tp2.data.network.flights

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class FlightService {
    val BASE_URL: String = "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/"
    suspend fun getFlights(): GetFlightsApiResponse {
        return withContext(Dispatchers.IO) {
            val retroFitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val retrofit = retroFitBuilder.create(FlightApiClient::class.java)

            retrofit.getFlights().body()!!;
        }
    }
}