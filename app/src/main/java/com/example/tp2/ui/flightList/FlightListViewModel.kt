package com.example.tp2.ui.flightList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp2.data.network.flights.FlightService
import com.example.tp2.data.network.flights.models.BestFlight
import kotlinx.coroutines.launch

class FlightListViewModel : ViewModel() {
    private val flightService = FlightService()

    private val _bestFlights = MutableLiveData<MutableList<BestFlight>>()
    val bestFlights: LiveData<MutableList<BestFlight>> = _bestFlights

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getBestFlights() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = flightService.getFlights()
                _bestFlights.value = response.best_flights.toMutableList()
            } catch (e: Exception) {
                throw e
            }
            finally {
                _isLoading.value = false
            }
        }
    }

}