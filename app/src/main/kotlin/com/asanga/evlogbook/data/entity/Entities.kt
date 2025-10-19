package com.asanga.evlogbook.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

@Entity(tableName = "trips")
data class Trip(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dateTime: Instant,
    val startOdometerKm: Double,
    val endOdometerKm: Double,
    val distanceKm: Double,
    val energyKWh: Double? = null,
    val notes: String? = null
)

@Entity(tableName = "charge_sessions")
data class ChargeSession(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dateTime: Instant,
    val energyKWh: Double,
    val unitPrice: Double,
    val cost: Double,
    val location: String? = null,
    val notes: String? = null
)

@Entity(tableName = "app_prefs")
data class AppPrefs(
    @PrimaryKey
    val id: Int = 1,
    val currencyCode: String = "LKR",
    val defaultUnitPrice: Double = 62.0,
    val defaultEfficiencyKWhPer100Km: Double = 14.0,
    val themeMode: String = "system" // system, light, dark
)

class InstantConverter {
    @TypeConverter
    fun fromInstant(value: Instant?): Long? = value?.toEpochMilliseconds()

    @TypeConverter
    fun toInstant(value: Long?): Instant? = value?.let {
        Instant.fromEpochMilliseconds(it)
    }
}
