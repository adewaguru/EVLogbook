package com.asanga.evlogbook.ui.screens.charge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asanga.evlogbook.data.entity.ChargeSession
import com.asanga.evlogbook.data.repository.ChargeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChargeListViewModel @Inject constructor(
    private val chargeRepository: ChargeRepository
) : ViewModel() {

    val charges: StateFlow<List<ChargeSession>> = chargeRepository.getAllCharges()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteCharge(charge: ChargeSession) {
        viewModelScope.launch {
            chargeRepository.deleteCharge(charge)
        }
    }
}
