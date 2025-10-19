package com.asanga.evlogbook.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asanga.evlogbook.data.db.AppDatabase
import com.asanga.evlogbook.data.entity.Trip
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.minus
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class TripDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var tripDao: TripDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        
        tripDao = database.tripDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertTrip_shouldInsertAndReturnTrip() = runTest {
        val trip = Trip(
            dateTime = Clock.System.now(),
            startOdometerKm = 100.0,
            endOdometerKm = 150.0,
            distanceKm = 50.0,
            energyKWh = 7.0,
            notes = "Test trip"
        )

        val insertedId = tripDao.insert(trip)
        assertTrue(insertedId > 0)

        val retrievedTrip = tripDao.getTripById(insertedId)
        assertNotNull(retrievedTrip)
        assertEquals(trip.startOdometerKm, retrievedTrip.startOdometerKm)
        assertEquals(trip.endOdometerKm, retrievedTrip.endOdometerKm)
        assertEquals(trip.distanceKm, retrievedTrip.distanceKm)
        assertEquals(trip.energyKWh, retrievedTrip.energyKWh)
        assertEquals(trip.notes, retrievedTrip.notes)
    }

    @Test
    fun getAllTrips_shouldReturnAllTripsOrderedByDateDesc() = runTest {
        val now = Clock.System.now()
        val trip1 = Trip(
            dateTime = now.minus(2, DateTimeUnit.DAY),
            startOdometerKm = 100.0,
            endOdometerKm = 150.0,
            distanceKm = 50.0
        )
        val trip2 = Trip(
            dateTime = now.minus(1, DateTimeUnit.DAY),
            startOdometerKm = 150.0,
            endOdometerKm = 200.0,
            distanceKm = 50.0
        )
        val trip3 = Trip(
            dateTime = now,
            startOdometerKm = 200.0,
            endOdometerKm = 250.0,
            distanceKm = 50.0
        )

        tripDao.insert(trip1)
        tripDao.insert(trip2)
        tripDao.insert(trip3)

        val trips = tripDao.getAllTrips().first()
        assertEquals(3, trips.size)
        
        // Should be ordered by date descending (newest first)
        assertTrue(trips[0].dateTime >= trips[1].dateTime)
        assertTrue(trips[1].dateTime >= trips[2].dateTime)
    }

    @Test
    fun getTripsInDateRange_shouldReturnTripsWithinRange() = runTest {
        val now = Clock.System.now()
        val startDate = now.minus(5, DateTimeUnit.DAY)
        val endDate = now.minus(1, DateTimeUnit.DAY)
        
        val tripInRange = Trip(
            dateTime = now.minus(3, DateTimeUnit.DAY),
            startOdometerKm = 100.0,
            endOdometerKm = 150.0,
            distanceKm = 50.0
        )
        val tripOutOfRange = Trip(
            dateTime = now,
            startOdometerKm = 150.0,
            endOdometerKm = 200.0,
            distanceKm = 50.0
        )

        tripDao.insert(tripInRange)
        tripDao.insert(tripOutOfRange)

        val tripsInRange = tripDao.getTripsInDateRange(startDate, endDate).first()
        assertEquals(1, tripsInRange.size)
        assertEquals(tripInRange.startOdometerKm, tripsInRange[0].startOdometerKm)
    }

    @Test
    fun updateTrip_shouldUpdateExistingTrip() = runTest {
        val trip = Trip(
            dateTime = Clock.System.now(),
            startOdometerKm = 100.0,
            endOdometerKm = 150.0,
            distanceKm = 50.0,
            notes = "Original notes"
        )

        val insertedId = tripDao.insert(trip)
        val updatedTrip = trip.copy(
            id = insertedId,
            notes = "Updated notes",
            energyKWh = 8.0
        )

        tripDao.update(updatedTrip)

        val retrievedTrip = tripDao.getTripById(insertedId)
        assertNotNull(retrievedTrip)
        assertEquals("Updated notes", retrievedTrip.notes)
        assertEquals(8.0, retrievedTrip.energyKWh)
    }

    @Test
    fun deleteTrip_shouldRemoveTrip() = runTest {
        val trip = Trip(
            dateTime = Clock.System.now(),
            startOdometerKm = 100.0,
            endOdometerKm = 150.0,
            distanceKm = 50.0
        )

        val insertedId = tripDao.insert(trip)
        val insertedTrip = tripDao.getTripById(insertedId)
        assertNotNull(insertedTrip)

        tripDao.delete(insertedTrip)

        val deletedTrip = tripDao.getTripById(insertedId)
        assertNull(deletedTrip)
    }

    @Test
    fun getTotalDistanceInDateRange_shouldCalculateCorrectTotal() = runTest {
        val now = Clock.System.now()
        val startDate = now.minus(5, DateTimeUnit.DAY)
        val endDate = now
        
        val trip1 = Trip(
            dateTime = now.minus(3, DateTimeUnit.DAY),
            startOdometerKm = 100.0,
            endOdometerKm = 150.0,
            distanceKm = 50.0
        )
        val trip2 = Trip(
            dateTime = now.minus(2, DateTimeUnit.DAY),
            startOdometerKm = 150.0,
            endOdometerKm = 225.0,
            distanceKm = 75.0
        )

        tripDao.insert(trip1)
        tripDao.insert(trip2)

        val totalDistance = tripDao.getTotalDistanceInDateRange(startDate, endDate).first()
        assertEquals(125.0, totalDistance ?: 0.0)
    }

    @Test
    fun getTotalEnergyInDateRange_shouldCalculateCorrectTotal() = runTest {
        val now = Clock.System.now()
        val startDate = now.minus(5, DateTimeUnit.DAY)
        val endDate = now
        
        val trip1 = Trip(
            dateTime = now.minus(3, DateTimeUnit.DAY),
            startOdometerKm = 100.0,
            endOdometerKm = 150.0,
            distanceKm = 50.0,
            energyKWh = 7.0
        )
        val trip2 = Trip(
            dateTime = now.minus(2, DateTimeUnit.DAY),
            startOdometerKm = 150.0,
            endOdometerKm = 225.0,
            distanceKm = 75.0,
            energyKWh = 10.5
        )

        tripDao.insert(trip1)
        tripDao.insert(trip2)

        val totalEnergy = tripDao.getTotalEnergyInDateRange(startDate, endDate).first()
        assertEquals(17.5, totalEnergy ?: 0.0)
    }
}
