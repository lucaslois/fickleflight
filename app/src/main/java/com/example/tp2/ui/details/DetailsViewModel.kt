package com.example.tp2.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp2.data.network.flights.FlightService
import com.example.tp2.data.network.flights.models.Details
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val flightService = FlightService()

    private val _flightDetails = MutableLiveData<Details>()
    val flightDetails: LiveData<Details> = _flightDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getFlightDetails(flightId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = flightService.getDetails(flightId)
                _flightDetails.value = response.data
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}