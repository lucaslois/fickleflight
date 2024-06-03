package com.example.tp2.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp2.data.network.flights.FlightService
import com.example.tp2.data.network.flights.models.Offer
import kotlinx.coroutines.launch

class OffersViewModel : ViewModel() {

    private val flightService = FlightService()

    private val _activeOffers = MutableLiveData<MutableList<Offer>>()
    val activeOffers: LiveData<MutableList<Offer>> = _activeOffers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getActiveOffers() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = flightService.getActiveOffers()
                _activeOffers.value = response.data.toMutableList()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}