package com.asanga.evlogbook.ui.screens.charge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asanga.evlogbook.data.entity.ChargeSession
import com.asanga.evlogbook.data.repository.ChargeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditChargeViewModel @Inject constructor(
    private val chargeRepository: ChargeRepository
) : ViewModel() {

    fun getChargeById(id: Long): Flow<ChargeSession?> {
        return chargeRepository.getChargeById(id)
    }

    fun saveCharge(charge: ChargeSession) {
        viewModelScope.launch {
            chargeRepository.upsertCharge(charge)
        }
    }

    fun deleteCharge(charge: ChargeSession) {
        viewModelScope.launch {
            chargeRepository.deleteCharge(charge)
        }
    }
}
