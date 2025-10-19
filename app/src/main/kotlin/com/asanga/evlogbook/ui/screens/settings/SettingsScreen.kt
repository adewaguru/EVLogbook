package com.asanga.evlogbook.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asanga.evlogbook.R
import com.asanga.evlogbook.data.entity.AppPrefs
import com.asanga.evlogbook.utils.Validators
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onNavigateToAbout: () -> Unit
) {
    val prefs by viewModel.prefs.collectAsState(initial = AppPrefs())
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var currencyCode by remember { mutableStateOf(prefs.currencyCode) }
    var defaultUnitPrice by remember { mutableStateOf(prefs.defaultUnitPrice.toString()) }
    var defaultEfficiency by remember { mutableStateOf(prefs.defaultEfficiencyKWhPer100Km.toString()) }
    var themeMode by remember { mutableStateOf(prefs.themeMode) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings)) }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Currency Code
            OutlinedTextField(
                value = currencyCode,
                onValueChange = { currencyCode = it.uppercase() },
                label = { Text(stringResource(R.string.currency_code)) },
                placeholder = { Text("LKR") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Default Unit Price
            OutlinedTextField(
                value = defaultUnitPrice,
                onValueChange = { defaultUnitPrice = it },
                label = { Text(stringResource(R.string.default_unit_price)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                isError = defaultUnitPrice.isNotBlank() && !Validators.validateUnitPrice(defaultUnitPrice).isValid
            )

            // Default Efficiency
            OutlinedTextField(
                value = defaultEfficiency,
                onValueChange = { defaultEfficiency = it },
                label = { Text(stringResource(R.string.default_efficiency)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                isError = defaultEfficiency.isNotBlank() && !Validators.validateEnergyConsumption(defaultEfficiency).isValid
            )

            // Theme Mode (for future implementation)
            OutlinedTextField(
                value = themeMode,
                onValueChange = { themeMode = it },
                label = { Text(stringResource(R.string.theme_mode)) },
                placeholder = { Text("system") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false, // For MVP, just show current value
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // About Button
            Button(
                onClick = onNavigateToAbout,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Info, contentDescription = stringResource(R.string.about))
                Spacer(modifier = Modifier.height(8.dp))
                Text(stringResource(R.string.about))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Save Button
            Button(
                onClick = {
                    // Validate and save
                    val unitPrice = defaultUnitPrice.toDoubleOrNull() ?: 62.0
                    val efficiency = defaultEfficiency.toDoubleOrNull() ?: 14.0

                    if (unitPrice < 0 || efficiency <= 0) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                context.getString(R.string.invalid_settings)
                            )
                        }
                        return@Button
                    }

                    val updatedPrefs = prefs.copy(
                        currencyCode = currencyCode.ifBlank { "LKR" },
                        defaultUnitPrice = unitPrice,
                        defaultEfficiencyKWhPer100Km = efficiency,
                        themeMode = themeMode
                    )

                    scope.launch {
                        viewModel.updatePrefs(updatedPrefs)
                        snackbarHostState.showSnackbar(
                            context.getString(R.string.settings_saved)
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Save, contentDescription = stringResource(R.string.save))
                Spacer(modifier = Modifier.height(8.dp))
                Text(stringResource(R.string.save))
            }
        }
    }
}
