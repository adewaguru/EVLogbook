package com.asanga.evlogbook.domain.usecase

import com.asanga.evlogbook.data.entity.ChargeSession
import com.asanga.evlogbook.data.entity.Trip
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UseCasesTest {

    @Test
    fun `CalculateTripDistanceUseCase returns correct distance for valid inputs`() {
        val useCase = CalculateTripDistanceUseCase()
        val result = useCase(100.0, 150.0)

        assertTrue(result.isSuccess)
        assertEquals(50.0, result.getOrNull())
    }

    @Test
    fun `CalculateTripDistanceUseCase fails for invalid inputs`() {
        val useCase = CalculateTripDistanceUseCase()
        val result = useCase(150.0, 100.0)

        assertTrue(result.isFailure)
        assertEquals("End odometer must be greater than or equal to start odometer", result.exceptionOrNull()?.message)
    }

    @Test
    fun `CalculateChargeCostUseCase returns correct cost for valid inputs`() {
        val useCase = CalculateChargeCostUseCase()
        val result = useCase(50.0, 62.0)

        assertTrue(result.isSuccess)
        assertEquals(3100.0, result.getOrNull())
    }

    @Test
    fun `ValidateTripUseCase validates correct trip data`() {
        val useCase = ValidateTripUseCase(CalculateTripDistanceUseCase())
        val trip = Trip(
            dateTime = Clock.System.now(),
            startOdometerKm = 100.0,
            endOdometerKm = 150.0,
            distanceKm = 50.0,
            energyKWh = 7.0
        )

        val result = useCase(trip)
        assertTrue(result.isValid)
        assertTrue(result.errors.isEmpty())
    }

    @Test
    fun `ValidateTripUseCase fails for invalid trip data`() {
        val useCase = ValidateTripUseCase(CalculateTripDistanceUseCase())
        val trip = Trip(
            dateTime = Clock.System.now(),
            startOdometerKm = 150.0, // Invalid: start > end
            endOdometerKm = 100.0,
            distanceKm = -50.0,
            energyKWh = -7.0 // Invalid: negative energy
        )

        val result = useCase(trip)
        assertFalse(result.isValid)
        assertTrue(result.errors.isNotEmpty())
    }

    @Test
    fun `ValidateChargeUseCase validates correct charge data`() {
        val useCase = ValidateChargeUseCase(CalculateChargeCostUseCase())
        val charge = ChargeSession(
            dateTime = Clock.System.now(),
            energyKWh = 50.0,
            unitPrice = 62.0,
            cost = 3100.0
        )

        val result = useCase(charge)
        assertTrue(result.isValid)
        assertTrue(result.errors.isEmpty())
    }

    @Test
    fun `EstimateTripEnergyUseCase calculates correct energy estimate`() {
        val useCase = EstimateTripEnergyUseCase()
        val result = useCase(100.0, 14.0) // 100km at 14kWh/100km

        assertEquals(14.0, result)
    }
}
