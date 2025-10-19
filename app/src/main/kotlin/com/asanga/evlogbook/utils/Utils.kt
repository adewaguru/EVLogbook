package com.asanga.evlogbook.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.LocalDate
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

object Formatters {

    private val distanceFormat = DecimalFormat("#,##0.0")
    private val energyFormat = DecimalFormat("#,##0.00")
    private val costFormat = DecimalFormat("#,##0.00")
    private val efficiencyFormat = DecimalFormat("#,##0.0")

    fun formatDistance(km: Double): String {
        return "${distanceFormat.format(km)} km"
    }

    fun formatEnergy(kWh: Double): String {
        return "${energyFormat.format(kWh)} kWh"
    }

    fun formatCost(amount: Double, currencyCode: String): String {
        return try {
            val currency = Currency.getInstance(currencyCode)
            val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
            format.currency = currency
            format.format(amount)
        } catch (e: Exception) {
            "${costFormat.format(amount)} $currencyCode"
        }
    }

    fun formatEfficiency(kWhPer100Km: Double): String {
        return "${efficiencyFormat.format(kWhPer100Km)} kWh/100km"
    }

    fun formatDateTime(instant: Instant): String {
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localDateTime.date} ${formatTime(localDateTime)}"
    }

    fun formatDate(instant: Instant): String {
        return instant.toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()
    }

    fun formatTime(localDateTime: kotlinx.datetime.LocalDateTime): String {
        return localDateTime.time.toString()
    }

    fun formatOdometerReading(km: Double): String {
        return distanceFormat.format(km)
    }

    fun formatUnitPrice(price: Double, currencyCode: String): String {
        return "${formatCost(price, currencyCode)}/kWh"
    }
}

object Validators {

    fun validateOdometerReading(value: String): ValidationResult {
        return try {
            val doubleValue = value.toDouble()
            if (doubleValue < 0) {
                ValidationResult(false, "Odometer reading must be non-negative")
            } else {
                ValidationResult(true)
            }
        } catch (e: NumberFormatException) {
            ValidationResult(false, "Invalid number format")
        }
    }

    fun validateEnergyConsumption(value: String): ValidationResult {
        return try {
            val doubleValue = value.toDouble()
            if (doubleValue <= 0) {
                ValidationResult(false, "Energy consumption must be positive")
            } else {
                ValidationResult(true)
            }
        } catch (e: NumberFormatException) {
            ValidationResult(false, "Invalid number format")
        }
    }

    fun validateUnitPrice(value: String): ValidationResult {
        return try {
            val doubleValue = value.toDouble()
            if (doubleValue < 0) {
                ValidationResult(false, "Unit price must be non-negative")
            } else {
                ValidationResult(true)
            }
        } catch (e: NumberFormatException) {
            ValidationResult(false, "Invalid number format")
        }
    }

    fun validateDistance(start: Double, end: Double): ValidationResult {
        return if (end < start) {
            ValidationResult(false, "End odometer must be greater than or equal to start odometer")
        } else if (start < 0) {
            ValidationResult(false, "Start odometer must be non-negative")
        } else {
            ValidationResult(true)
        }
    }
}

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)

object ChartUtils {

    fun generateSparklineData(dailyStats: List<com.asanga.evlogbook.data.repository.DailyStats>): List<Float> {
        return dailyStats.map { it.distance.toFloat() }
    }

    fun generateEnergySparklineData(dailyStats: List<com.asanga.evlogbook.data.repository.DailyStats>): List<Float> {
        return dailyStats.map { it.energy.toFloat() }
    }
}

object DateTimeUtils {

    fun getCurrentMonthStart(): Instant {
        val now = kotlinx.datetime.Clock.System.now()
        val tz = TimeZone.currentSystemDefault()
        val ldt = now.toLocalDateTime(tz)
        val startDate = LocalDate(ldt.year, ldt.monthNumber, 1)
        return startDate.atStartOfDayIn(tz)
    }

    fun getCurrentMonthEnd(): Instant {
        val tz = TimeZone.currentSystemDefault()
        val start = getCurrentMonthStart().toLocalDateTime(tz)
        val nextMonthStartDate = LocalDate(start.year, start.monthNumber, 1).plus(DatePeriod(months = 1))
        return nextMonthStartDate.atStartOfDayIn(tz)
    }

    fun getMonthStart(year: Int, month: Int): Instant {
        val tz = TimeZone.currentSystemDefault()
        val startDate = LocalDate(year, month, 1)
        return startDate.atStartOfDayIn(tz)
    }

    fun getMonthEnd(year: Int, month: Int): Instant {
        val tz = TimeZone.currentSystemDefault()
        val nextMonthStart = LocalDate(year, month, 1).plus(DatePeriod(months = 1))
        return nextMonthStart.atStartOfDayIn(tz)
    }
}
