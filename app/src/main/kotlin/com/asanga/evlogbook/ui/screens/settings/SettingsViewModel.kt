package com.asanga.evlogbook.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asanga.evlogbook.data.entity.AppPrefs
import com.asanga.evlogbook.data.repository.PrefsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val prefsRepository: PrefsRepository
) : ViewModel() {

    val prefs: StateFlow<AppPrefs> = prefsRepository.getPrefs()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AppPrefs()
        )

    fun updatePrefs(prefs: AppPrefs) {
        viewModelScope.launch {
            prefsRepository.updatePrefs(prefs)
        }
    }
}
