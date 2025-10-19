package com.asanga.evlogbook.domain.usecase

import com.asanga.evlogbook.data.entity.ChargeSession
import com.asanga.evlogbook.data.entity.Trip
import com.asanga.evlogbook.data.repository.ChargeRepository
import com.asanga.evlogbook.data.repository.PrefsRepository
import com.asanga.evlogbook.data.repository.TripRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.Currency
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDashboardDataUseCase @Inject constructor(
    private val tripRepository: TripRepository,
    private val chargeRepository: ChargeRepository,
    private val prefsRepository: PrefsRepository
) {

    operator fun invoke(): Flow<DashboardData> {
        return combine(
            tripRepository.getCurrentMonthStats(),
            chargeRepository.getCurrentMonthStats(),
            prefsRepository.getPrefs(),
            tripRepository.getCurrentMonthDailyStats(14.0) // Default efficiency for chart
        ) { tripStats, chargeStats, prefs, dailyTripStats ->

            val combinedStats = combineMonthlyStats(tripStats, chargeStats)
            val avgConsumption = calculateAverageConsumption(combinedStats, prefs.defaultEfficiencyKWhPer100Km)

            DashboardData(
                totalDistance = combinedStats.totalDistance,
                totalEnergy = combinedStats.totalEnergy,
                totalCost = combinedStats.totalCost,
                averageConsumption = avgConsumption,
                tripCount = combinedStats.tripCount,
                chargeCount = combinedStats.chargeCount,
                currencySymbol = getCurrencySymbol(prefs.currencyCode),
                recentTrips = emptyList(), // Will be populated from recent trips query
                recentCharges = emptyList(), // Will be populated from recent charges query
                weeklyChartData = dailyTripStats.take(7)
            )
        }
    }

    private fun combineMonthlyStats(tripStats: com.asanga.evlogbook.data.repository.MonthlyStats?,
                                   chargeStats: com.asanga.evlogbook.data.repository.MonthlyStats?): com.asanga.evlogbook.data.repository.MonthlyStats {
        return com.asanga.evlogbook.data.repository.MonthlyStats(
            totalDistance = tripStats?.totalDistance ?: 0.0,
            totalEnergy = (tripStats?.totalEnergy ?: 0.0) + (chargeStats?.totalEnergy ?: 0.0),
            totalCost = chargeStats?.totalCost ?: 0.0,
            tripCount = tripStats?.tripCount ?: 0,
            chargeCount = chargeStats?.chargeCount ?: 0
        )
    }

    private fun calculateAverageConsumption(stats: com.asanga.evlogbook.data.repository.MonthlyStats,
                                           defaultEfficiency: Double): Double {
        val totalEnergy = stats.totalEnergy
        val totalDistance = stats.totalDistance

        return if (totalDistance > 0 && totalEnergy > 0) {
            (totalEnergy / totalDistance) * 100.0
        } else {
            defaultEfficiency
        }
    }

    private fun getCurrencySymbol(currencyCode: String): String {
        return try {
            Currency.getInstance(currencyCode).symbol
        } catch (e: Exception) {
            currencyCode
        }
    }
}

@Singleton
class CalculateTripDistanceUseCase @Inject constructor() {

    operator fun invoke(startOdometer: Double, endOdometer: Double): Result<Double> {
        return if (endOdometer < startOdometer) {
            Result.failure(IllegalArgumentException("End odometer must be greater than or equal to start odometer"))
        } else if (startOdometer < 0) {
            Result.failure(IllegalArgumentException("Start odometer must be non-negative"))
        } else {
            Result.success(endOdometer - startOdometer)
        }
    }
}

@Singleton
class CalculateChargeCostUseCase @Inject constructor() {

    operator fun invoke(energyKWh: Double, unitPrice: Double): Result<Double> {
        return if (energyKWh <= 0) {
            Result.failure(IllegalArgumentException("Energy must be positive"))
        } else if (unitPrice < 0) {
            Result.failure(IllegalArgumentException("Unit price must be non-negative"))
        } else {
            Result.success(energyKWh * unitPrice)
        }
    }
}

@Singleton
class EstimateTripEnergyUseCase @Inject constructor() {

    operator fun invoke(distanceKm: Double, efficiencyKWhPer100Km: Double): Double {
        return (distanceKm * efficiencyKWhPer100Km) / 100.0
    }
}

@Singleton
class ValidateTripUseCase @Inject constructor(
    private val calculateTripDistance: CalculateTripDistanceUseCase
) {

    operator fun invoke(trip: Trip): ValidationResult {
        val errors = mutableListOf<String>()

        if (trip.startOdometerKm < 0) {
            errors.add("Start odometer must be non-negative")
        }

        calculateTripDistance(trip.startOdometerKm, trip.endOdometerKm)
            .onFailure { errors.add(it.message ?: "Invalid distance calculation") }

        if (trip.energyKWh != null && trip.energyKWh <= 0) {
            errors.add("Energy consumption must be positive if provided")
        }

        return ValidationResult(errors.isEmpty(), errors)
    }
}

@Singleton
class ValidateChargeUseCase @Inject constructor(
    private val calculateChargeCost: CalculateChargeCostUseCase
) {

    operator fun invoke(charge: ChargeSession): ValidationResult {
        val errors = mutableListOf<String>()

        if (charge.energyKWh <= 0) {
            errors.add("Energy must be positive")
        }

        if (charge.unitPrice < 0) {
            errors.add("Unit price must be non-negative")
        }

        calculateChargeCost(charge.energyKWh, charge.unitPrice)
            .onFailure { errors.add(it.message ?: "Invalid cost calculation") }

        return ValidationResult(errors.isEmpty(), errors)
    }
}

data class DashboardData(
    val totalDistance: Double,
    val totalEnergy: Double,
    val totalCost: Double,
    val averageConsumption: Double,
    val tripCount: Int,
    val chargeCount: Int,
    val currencySymbol: String,
    val recentTrips: List<Trip>,
    val recentCharges: List<ChargeSession>,
    val weeklyChartData: List<com.asanga.evlogbook.data.repository.DailyStats>
)

data class ValidationResult(
    val isValid: Boolean,
    val errors: List<String>
)
