package com.asanga.evlogbook.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asanga.evlogbook.domain.usecase.DashboardData
import com.asanga.evlogbook.domain.usecase.GetDashboardDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDashboardDataUseCase: GetDashboardDataUseCase
) : ViewModel() {

    val dashboardData: StateFlow<DashboardData> = getDashboardDataUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DashboardData(
                totalDistance = 0.0,
                totalEnergy = 0.0,
                totalCost = 0.0,
                averageConsumption = 14.0,
                tripCount = 0,
                chargeCount = 0,
                currencySymbol = "LKR",
                recentTrips = emptyList(),
                recentCharges = emptyList(),
                weeklyChartData = emptyList()
            )
        )
}
