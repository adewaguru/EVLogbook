package com.asanga.evlogbook.ui.screens.trip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asanga.evlogbook.data.entity.Trip
import com.asanga.evlogbook.data.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTripViewModel @Inject constructor(
    private val tripRepository: TripRepository
) : ViewModel() {

    fun getTripById(id: Long): Flow<Trip?> {
        return tripRepository.getTripById(id)
    }

    fun saveTrip(trip: Trip) {
        viewModelScope.launch {
            tripRepository.upsertTrip(trip)
        }
    }

    fun deleteTrip(trip: Trip) {
        viewModelScope.launch {
            tripRepository.deleteTrip(trip)
        }
    }
}
