package com.asanga.evlogbook.ui.screens.trip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asanga.evlogbook.R
import com.asanga.evlogbook.data.entity.Trip
import com.asanga.evlogbook.domain.usecase.EstimateTripEnergyUseCase
import com.asanga.evlogbook.domain.usecase.ValidateTripUseCase
import com.asanga.evlogbook.utils.Formatters
import com.asanga.evlogbook.utils.Validators
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTripScreen(
    viewModel: AddEditTripViewModel = hiltViewModel(),
    tripId: Long = 0L,
    onNavigateBack: () -> Unit
) {
    val trip by viewModel.getTripById(tripId).collectAsState(initial = null)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var dateTime by remember { mutableStateOf(Clock.System.now()) }
    var startOdometer by remember { mutableStateOf("") }
    var endOdometer by remember { mutableStateOf("") }
    var energyKWh by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    // Initialize form with existing trip data
    trip?.let {
        dateTime = it.dateTime
        startOdometer = it.startOdometerKm.toString()
        endOdometer = it.endOdometerKm.toString()
        energyKWh = it.energyKWh?.toString() ?: ""
        notes = it.notes ?: ""
    }

    // Calculate distance and estimated energy
    val distance = try {
        if (startOdometer.isNotBlank() && endOdometer.isNotBlank()) {
            endOdometer.toDouble() - startOdometer.toDouble()
        } else 0.0
    } catch (e: NumberFormatException) {
        0.0
    }

    val estimatedEnergy = if (distance > 0) {
        EstimateTripEnergyUseCase().invoke(distance, 14.0) // Default efficiency
    } else 0.0

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (tripId == 0L) stringResource(R.string.add_trip)
                        else stringResource(R.string.edit_trip)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                },
                actions = {
                    if (tripId != 0L) {
                        IconButton(onClick = {
                            trip?.let {
                                scope.launch {
                                    viewModel.deleteTrip(it)
                                    snackbarHostState.showSnackbar(
                                        context.getString(R.string.trip_deleted)
                                    )
                                    onNavigateBack()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.delete_trip))
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
            // Date/Time - For now, use current time. In a full implementation, this would be a date/time picker
            OutlinedTextField(
                value = Formatters.formatDateTime(dateTime),
                onValueChange = { },
                readOnly = true,
                label = { Text(stringResource(R.string.date_time)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Start Odometer
            OutlinedTextField(
                value = startOdometer,
                onValueChange = { startOdometer = it },
                label = { Text(stringResource(R.string.start_odometer_km)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                isError = startOdometer.isNotBlank() && !Validators.validateOdometerReading(startOdometer).isValid
            )

            // End Odometer
            OutlinedTextField(
                value = endOdometer,
                onValueChange = { endOdometer = it },
                label = { Text(stringResource(R.string.end_odometer_km)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                isError = endOdometer.isNotBlank() && !Validators.validateOdometerReading(endOdometer).isValid
            )

            // Distance (calculated)
            OutlinedTextField(
                value = if (distance > 0) Formatters.formatDistance(distance) else "",
                onValueChange = { },
                readOnly = true,
                label = { Text(stringResource(R.string.distance_km)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Energy Consumption (optional)
            OutlinedTextField(
                value = energyKWh,
                onValueChange = { energyKWh = it },
                label = { Text(stringResource(R.string.energy_consumption_kwh_optional)) },
                placeholder = {
                    Text(
                        if (estimatedEnergy > 0) stringResource(
                            R.string.estimated_energy,
                            Formatters.formatEnergy(estimatedEnergy)
                        ) else stringResource(R.string.energy_consumption_kwh)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                isError = energyKWh.isNotBlank() && !Validators.validateEnergyConsumption(energyKWh).isValid
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
                    val startOdo = startOdometer.toDoubleOrNull() ?: 0.0
                    val endOdo = endOdometer.toDoubleOrNull() ?: 0.0
                    val energy = energyKWh.toDoubleOrNull()

                    val tripToSave = Trip(
                        id = tripId,
                        dateTime = dateTime,
                        startOdometerKm = startOdo,
                        endOdometerKm = endOdo,
                        distanceKm = distance,
                        energyKWh = energy,
                        notes = notes.takeIf { it.isNotBlank() }
                    )

                    val validation = ValidateTripUseCase(
                        com.asanga.evlogbook.domain.usecase.CalculateTripDistanceUseCase()
                    ).invoke(tripToSave)

                    if (validation.isValid) {
                        scope.launch {
                            viewModel.saveTrip(tripToSave)
                            snackbarHostState.showSnackbar(
                                context.getString(
                                    if (tripId == 0L) R.string.trip_added else R.string.trip_updated
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
                enabled = startOdometer.isNotBlank() && endOdometer.isNotBlank()
            ) {
                Icon(Icons.Default.Save, contentDescription = stringResource(R.string.save))
                Spacer(modifier = Modifier.height(8.dp))
                Text(stringResource(R.string.save))
            }
        }
    }
}
