package com.asanga.evlogbook.data.repository

import com.asanga.evlogbook.data.dao.ChargeDao
import com.asanga.evlogbook.data.dao.MonthlyChargeStats
import com.asanga.evlogbook.data.dao.PrefsDao
import com.asanga.evlogbook.data.dao.TripDao
import com.asanga.evlogbook.data.entity.AppPrefs
import com.asanga.evlogbook.data.entity.ChargeSession
import com.asanga.evlogbook.data.entity.Trip
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.toInstant
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepository @Inject constructor(
    private val tripDao: TripDao
) {

    fun getAllTrips(): Flow<List<Trip>> = tripDao.getAllTrips()

    fun getTripById(id: Long): Flow<Trip?> = tripDao.getTripById(id)

    fun getTripsByDateRange(startDate: Instant, endDate: Instant): Flow<List<Trip>> =
        tripDao.getTripsByDateRange(startDate, endDate)

    fun getCurrentMonthStats(): Flow<MonthlyStats?> {
        val now = Clock.System.now()
        val localDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
        val tz = TimeZone.currentSystemDefault()
        val startDate = LocalDate(
            localDateTime.year,
            localDateTime.monthNumber,
            1
        )
        val startOfMonth = startDate.atStartOfDayIn(tz)
        val startOfNextMonth = startDate
            .plus(1, DateTimeUnit.MONTH)
            .atStartOfDayIn(tz)

        return tripDao.getMonthlyStats(startOfMonth, startOfNextMonth)
            .map { stats ->
                stats?.let {
                    MonthlyStats(
                        totalDistance = it.totalDistance ?: 0.0,
                        totalEnergy = it.totalEnergy ?: 0.0,
                        tripCount = it.tripCount ?: 0
                    )
                }
            }
    }

    fun getCurrentMonthDailyStats(efficiency: Double): Flow<List<DailyStats>> {
        val now = Clock.System.now()
        val localDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
        val tz = TimeZone.currentSystemDefault()
        val startDate = LocalDate(
            localDateTime.year,
            localDateTime.monthNumber,
            1
        )
        val startOfMonth = startDate.atStartOfDayIn(tz)
        val startOfNextMonth = startDate
            .plus(1, DateTimeUnit.MONTH)
            .atStartOfDayIn(tz)

        return tripDao.getDailyTripStats(startOfMonth, startOfNextMonth, efficiency)
            .map { list ->
                list.map {
                    DailyStats(
                        date = it.date,
                        distance = it.totalDistance ?: 0.0,
                        energy = it.totalEnergy ?: 0.0
                    )
                }
            }
    }

    suspend fun upsertTrip(trip: Trip) = tripDao.upsertTrip(trip)

    suspend fun deleteTrip(trip: Trip) = tripDao.deleteTrip(trip)

    suspend fun deleteAllTrips() = tripDao.deleteAllTrips()

    fun getTripCount(): Flow<Int> = tripDao.getTripCount()
}

@Singleton
class ChargeRepository @Inject constructor(
    private val chargeDao: ChargeDao
) {

    fun getAllCharges(): Flow<List<ChargeSession>> = chargeDao.getAllCharges()

    fun getChargeById(id: Long): Flow<ChargeSession?> = chargeDao.getChargeById(id)

    fun getChargesByDateRange(startDate: Instant, endDate: Instant): Flow<List<ChargeSession>> =
        chargeDao.getChargesByDateRange(startDate, endDate)

    fun getCurrentMonthStats(): Flow<MonthlyStats?> {
        val now = Clock.System.now()
        val localDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
        val tz = TimeZone.currentSystemDefault()
        val startDate = LocalDate(
            localDateTime.year,
            localDateTime.monthNumber,
            1
        )
        val startOfMonth = startDate.atStartOfDayIn(tz)
        val startOfNextMonth = startDate
            .plus(1, DateTimeUnit.MONTH)
            .atStartOfDayIn(tz)

        return chargeDao.getMonthlyChargeStats(startOfMonth, startOfNextMonth)
            .map { stats ->
                stats?.let {
                    MonthlyStats(
                        totalEnergy = it.totalEnergy ?: 0.0,
                        totalCost = it.totalCost ?: 0.0,
                        chargeCount = it.chargeCount ?: 0
                    )
                }
            }
    }

    fun getCurrentMonthDailyStats(): Flow<List<DailyStats>> {
        val now = Clock.System.now()
        val localDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
        val tz = TimeZone.currentSystemDefault()
        val startDate = LocalDate(
            localDateTime.year,
            localDateTime.monthNumber,
            1
        )
        val startOfMonth = startDate.atStartOfDayIn(tz)
        val startOfNextMonth = startDate
            .plus(1, DateTimeUnit.MONTH)
            .atStartOfDayIn(tz)

        return chargeDao.getDailyChargeStats(startOfMonth, startOfNextMonth)
            .map { list ->
                list.map {
                    DailyStats(
                        date = it.date,
                        energy = it.totalEnergy ?: 0.0,
                        cost = it.totalCost ?: 0.0
                    )
                }
            }
    }

    suspend fun upsertCharge(charge: ChargeSession) = chargeDao.upsertCharge(charge)

    suspend fun deleteCharge(charge: ChargeSession) = chargeDao.deleteCharge(charge)

    suspend fun deleteAllCharges() = chargeDao.deleteAllCharges()

    fun getChargeCount(): Flow<Int> = chargeDao.getChargeCount()
}

@Singleton
class PrefsRepository @Inject constructor(
    private val prefsDao: PrefsDao
) {

    fun getPrefs(): Flow<AppPrefs> = prefsDao.getPrefs().map { it ?: getDefaultPrefs() }

    suspend fun updatePrefs(prefs: AppPrefs) = prefsDao.upsertPrefs(prefs)

    private fun getDefaultPrefs() = AppPrefs(
        currencyCode = "LKR",
        defaultUnitPrice = 62.0,
        defaultEfficiencyKWhPer100Km = 14.0,
        themeMode = "system"
    )
}

data class MonthlyStats(
    val totalDistance: Double = 0.0,
    val totalEnergy: Double = 0.0,
    val totalCost: Double = 0.0,
    val tripCount: Int = 0,
    val chargeCount: Int = 0
)

data class DailyStats(
    val date: String,
    val distance: Double = 0.0,
    val energy: Double = 0.0,
    val cost: Double = 0.0
)
