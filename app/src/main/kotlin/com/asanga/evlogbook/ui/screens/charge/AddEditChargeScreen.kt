package com.asanga.evlogbook.ui.screens.charge

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
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asanga.evlogbook.R
import com.asanga.evlogbook.data.entity.ChargeSession
import com.asanga.evlogbook.domain.usecase.CalculateChargeCostUseCase
import com.asanga.evlogbook.domain.usecase.ValidateChargeUseCase
import com.asanga.evlogbook.utils.Formatters
import com.asanga.evlogbook.utils.Validators
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditChargeScreen(
    viewModel: AddEditChargeViewModel = hiltViewModel(),
    chargeId: Long = 0L,
    onNavigateBack: () -> Unit
) {
    val charge by viewModel.getChargeById(chargeId).collectAsState(initial = null)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var dateTime by remember { mutableStateOf(Clock.System.now()) }
    var energyKWh by remember { mutableStateOf("") }
    var unitPrice by remember { mutableStateOf("62.0") } // Default from settings
    var location by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    // Initialize form with existing charge data
    charge?.let {
        dateTime = it.dateTime
        energyKWh = it.energyKWh.toString()
        unitPrice = it.unitPrice.toString()
        location = it.location ?: ""
        notes = it.notes ?: ""
    }

    // Calculate cost
    val cost = try {
        val energy = energyKWh.toDoubleOrNull() ?: 0.0
        val price = unitPrice.toDoubleOrNull() ?: 0.0
        CalculateChargeCostUseCase().invoke(energy, price).getOrDefault(0.0)
    } catch (e: Exception) {
        0.0
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (chargeId == 0L) stringResource(R.string.add_charge)
                        else stringResource(R.string.edit_charge)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                },
                actions = {
                    if (chargeId != 0L) {
                        IconButton(onClick = {
                            charge?.let {
                                scope.launch {
                                    viewModel.deleteCharge(it)
                                    snackbarHostState.showSnackbar(
                                        context.getString(R.string.charge_deleted)
                                    )
                                    onNavigateBack()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.delete_charge))
                        }
                    }
                }
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
            // Date/Time - For now, use current time
            OutlinedTextField(
                value = Formatters.formatDateTime(dateTime),
                onValueChange = { },
                readOnly = true,
                label = { Text(stringResource(R.string.date_time)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Energy (kWh)
            OutlinedTextField(
                value = energyKWh,
                onValueChange = { energyKWh = it },
                label = { Text(stringResource(R.string.energy_kwh)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                isError = energyKWh.isNotBlank() && !Validators.validateEnergyConsumption(energyKWh).isValid
            )

            // Unit Price
            OutlinedTextField(
                value = unitPrice,
                onValueChange = { unitPrice = it },
                label = { Text(stringResource(R.string.unit_price_per_kwh)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                isError = unitPrice.isNotBlank() && !Validators.validateUnitPrice(unitPrice).isValid
            )

            // Cost (calculated)
            OutlinedTextField(
                value = if (cost > 0) Formatters.formatCost(cost, "LKR") else "",
                onValueChange = { },
                readOnly = true,
                label = { Text(stringResource(R.string.total_cost)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Location
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text(stringResource(R.string.location_optional)) },
                placeholder = { Text(stringResource(R.string.location_placeholder)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Notes
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text(stringResource(R.string.notes)) },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Save Button
            Button(
                onClick = {
                    // Validate and save
                    val energy = energyKWh.toDoubleOrNull() ?: 0.0
                    val price = unitPrice.toDoubleOrNull() ?: 0.0

                    val chargeToSave = ChargeSession(
                        id = chargeId,
                        dateTime = dateTime,
                        energyKWh = energy,
                        unitPrice = price,
                        cost = cost,
                        location = location.takeIf { it.isNotBlank() },
                        notes = notes.takeIf { it.isNotBlank() }
                    )

                    val validation = ValidateChargeUseCase(CalculateChargeCostUseCase()).invoke(chargeToSave)

                    if (validation.isValid) {
                        scope.launch {
                            viewModel.saveCharge(chargeToSave)
                            snackbarHostState.showSnackbar(
                                context.getString(
                                    if (chargeId == 0L) R.string.charge_added else R.string.charge_updated
                                )
                            )
                            onNavigateBack()
                        }
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                validation.errors.joinToString(", ")
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = energyKWh.isNotBlank() && unitPrice.isNotBlank()
            ) {
                Icon(Icons.Default.Save, contentDescription = stringResource(R.string.save))
                Spacer(modifier = Modifier.height(8.dp))
                Text(stringResource(R.string.save))
            }
        }
    }
}
