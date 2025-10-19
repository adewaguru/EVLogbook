package com.asanga.evlogbook.ui.screens.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asanga.evlogbook.data.repository.ChargeRepository
import com.asanga.evlogbook.data.repository.MonthlyStats
import com.asanga.evlogbook.data.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val tripRepository: TripRepository,
    private val chargeRepository: ChargeRepository
) : ViewModel() {

    val currentMonthStats: StateFlow<MonthlyStats?> = tripRepository.getCurrentMonthStats()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val currentMonthDailyStats: StateFlow<List<com.asanga.evlogbook.data.repository.DailyStats>> = tripRepository.getCurrentMonthDailyStats(14.0)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
