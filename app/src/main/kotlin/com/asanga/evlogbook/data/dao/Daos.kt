package com.asanga.evlogbook.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.asanga.evlogbook.data.entity.AppPrefs
import com.asanga.evlogbook.data.entity.ChargeSession
import com.asanga.evlogbook.data.entity.Trip
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

@Dao
interface TripDao {

    @Query("SELECT * FROM trips ORDER BY dateTime DESC")
    fun getAllTrips(): Flow<List<Trip>>

    @Query("SELECT * FROM trips WHERE id = :id")
    fun getTripById(id: Long): Flow<Trip?>

    @Query("SELECT * FROM trips WHERE dateTime BETWEEN :startDate AND :endDate ORDER BY dateTime DESC")
    fun getTripsByDateRange(startDate: Instant, endDate: Instant): Flow<List<Trip>>

    @Query("""
        SELECT SUM(distanceKm) as totalDistance,
               SUM(energyKWh) as totalEnergy,
               COUNT(*) as tripCount
        FROM trips
        WHERE dateTime >= :startOfMonth AND dateTime < :startOfNextMonth
    """)
    fun getMonthlyStats(startOfMonth: Instant, startOfNextMonth: Instant): Flow<MonthlyTripStats?>

    @Query("""
        SELECT DATE(dateTime / 1000, 'unixepoch', 'localtime') as date,
               SUM(distanceKm) as totalDistance,
               SUM(COALESCE(energyKWh, distanceKm * :efficiency / 100.0)) as totalEnergy
        FROM trips
        WHERE dateTime >= :startOfMonth AND dateTime < :startOfNextMonth
        GROUP BY DATE(dateTime / 1000, 'unixepoch', 'localtime')
        ORDER BY date
    """)
    fun getDailyTripStats(startOfMonth: Instant, startOfNextMonth: Instant, efficiency: Double): Flow<List<DailyTripStats>>

    @Query("SELECT COUNT(*) FROM trips")
    fun getTripCount(): Flow<Int>

    @Upsert
    suspend fun upsertTrip(trip: Trip)

    @Delete
    suspend fun deleteTrip(trip: Trip)

    @Query("DELETE FROM trips")
    suspend fun deleteAllTrips()
}

@Dao
interface ChargeDao {

    @Query("SELECT * FROM charge_sessions ORDER BY dateTime DESC")
    fun getAllCharges(): Flow<List<ChargeSession>>

    @Query("SELECT * FROM charge_sessions WHERE id = :id")
    fun getChargeById(id: Long): Flow<ChargeSession?>

    @Query("SELECT * FROM charge_sessions WHERE dateTime BETWEEN :startDate AND :endDate ORDER BY dateTime DESC")
    fun getChargesByDateRange(startDate: Instant, endDate: Instant): Flow<List<ChargeSession>>

    @Query("""
        SELECT SUM(energyKWh) as totalEnergy,
               SUM(cost) as totalCost,
               COUNT(*) as chargeCount
        FROM charge_sessions
        WHERE dateTime >= :startOfMonth AND dateTime < :startOfNextMonth
    """)
    fun getMonthlyChargeStats(startOfMonth: Instant, startOfNextMonth: Instant): Flow<MonthlyChargeStats?>

    @Query("""
        SELECT DATE(dateTime / 1000, 'unixepoch', 'localtime') as date,
               SUM(energyKWh) as totalEnergy,
               SUM(cost) as totalCost
        FROM charge_sessions
        WHERE dateTime >= :startOfMonth AND dateTime < :startOfNextMonth
        GROUP BY DATE(dateTime / 1000, 'unixepoch', 'localtime')
        ORDER BY date
    """)
    fun getDailyChargeStats(startOfMonth: Instant, startOfNextMonth: Instant): Flow<List<DailyChargeStats>>

    @Query("SELECT COUNT(*) FROM charge_sessions")
    fun getChargeCount(): Flow<Int>

    @Upsert
    suspend fun upsertCharge(charge: ChargeSession)

    @Delete
    suspend fun deleteCharge(charge: ChargeSession)

    @Query("DELETE FROM charge_sessions")
    suspend fun deleteAllCharges()
}

@Dao
interface PrefsDao {

    @Query("SELECT * FROM app_prefs WHERE id = 1")
    fun getPrefs(): Flow<AppPrefs?>

    @Upsert
    suspend fun upsertPrefs(prefs: AppPrefs)
}

data class MonthlyTripStats(
    val totalDistance: Double?,
    val totalEnergy: Double?,
    val tripCount: Int?
)

data class MonthlyChargeStats(
    val totalEnergy: Double?,
    val totalCost: Double?,
    val chargeCount: Int?
)

data class DailyTripStats(
    val date: String,
    val totalDistance: Double?,
    val totalEnergy: Double?
)

data class DailyChargeStats(
    val date: String,
    val totalEnergy: Double?,
    val totalCost: Double?
)
