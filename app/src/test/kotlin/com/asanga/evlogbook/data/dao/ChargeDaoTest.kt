package com.asanga.evlogbook.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asanga.evlogbook.data.db.AppDatabase
import com.asanga.evlogbook.data.entity.ChargeSession
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
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
class ChargeDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var chargeDao: ChargeDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        
        chargeDao = database.chargeDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertCharge_shouldInsertAndReturnCharge() = runTest {
        val charge = ChargeSession(
            dateTime = Clock.System.now(),
            energyKWh = 50.0,
            unitPrice = 62.0,
            cost = 3100.0,
            location = "Home",
            notes = "Test charge"
        )

        val insertedId = chargeDao.insert(charge)
        assertTrue(insertedId > 0)

        val retrievedCharge = chargeDao.getChargeById(insertedId)
        assertNotNull(retrievedCharge)
        assertEquals(charge.energyKWh, retrievedCharge.energyKWh)
        assertEquals(charge.unitPrice, retrievedCharge.unitPrice)
        assertEquals(charge.cost, retrievedCharge.cost)
        assertEquals(charge.location, retrievedCharge.location)
        assertEquals(charge.notes, retrievedCharge.notes)
    }

    @Test
    fun getAllCharges_shouldReturnAllChargesOrderedByDateDesc() = runTest {
        val now = Clock.System.now()
        val charge1 = ChargeSession(
            dateTime = now.minus(2, DateTimeUnit.DAY),
            energyKWh = 30.0,
            unitPrice = 62.0,
            cost = 1860.0
        )
        val charge2 = ChargeSession(
            dateTime = now.minus(1, DateTimeUnit.DAY),
            energyKWh = 40.0,
            unitPrice = 62.0,
            cost = 2480.0
        )
        val charge3 = ChargeSession(
            dateTime = now,
            energyKWh = 50.0,
            unitPrice = 62.0,
            cost = 3100.0
        )

        chargeDao.insert(charge1)
        chargeDao.insert(charge2)
        chargeDao.insert(charge3)

        val charges = chargeDao.getAllCharges().first()
        assertEquals(3, charges.size)
        
        // Should be ordered by date descending (newest first)
        assertTrue(charges[0].dateTime >= charges[1].dateTime)
        assertTrue(charges[1].dateTime >= charges[2].dateTime)
    }

    @Test
    fun getChargesInDateRange_shouldReturnChargesWithinRange() = runTest {
        val now = Clock.System.now()
        val startDate = now.minus(5, DateTimeUnit.DAY)
        val endDate = now.minus(1, DateTimeUnit.DAY)
        
        val chargeInRange = ChargeSession(
            dateTime = now.minus(3, DateTimeUnit.DAY),
            energyKWh = 30.0,
            unitPrice = 62.0,
            cost = 1860.0
        )
        val chargeOutOfRange = ChargeSession(
            dateTime = now,
            energyKWh = 40.0,
            unitPrice = 62.0,
            cost = 2480.0
        )

        chargeDao.insert(chargeInRange)
        chargeDao.insert(chargeOutOfRange)

        val chargesInRange = chargeDao.getChargesInDateRange(startDate, endDate).first()
        assertEquals(1, chargesInRange.size)
        assertEquals(chargeInRange.energyKWh, chargesInRange[0].energyKWh)
    }

    @Test
    fun updateCharge_shouldUpdateExistingCharge() = runTest {
        val charge = ChargeSession(
            dateTime = Clock.System.now(),
            energyKWh = 30.0,
            unitPrice = 62.0,
            cost = 1860.0,
            location = "Home",
            notes = "Original notes"
        )

        val insertedId = chargeDao.insert(charge)
        val updatedCharge = charge.copy(
            id = insertedId,
            location = "Office",
            notes = "Updated notes",
            energyKWh = 35.0,
            cost = 2170.0
        )

        chargeDao.update(updatedCharge)

        val retrievedCharge = chargeDao.getChargeById(insertedId)
        assertNotNull(retrievedCharge)
        assertEquals("Office", retrievedCharge.location)
        assertEquals("Updated notes", retrievedCharge.notes)
        assertEquals(35.0, retrievedCharge.energyKWh)
        assertEquals(2170.0, retrievedCharge.cost)
    }

    @Test
    fun deleteCharge_shouldRemoveCharge() = runTest {
        val charge = ChargeSession(
            dateTime = Clock.System.now(),
            energyKWh = 30.0,
            unitPrice = 62.0,
            cost = 1860.0
        )

        val insertedId = chargeDao.insert(charge)
        val insertedCharge = chargeDao.getChargeById(insertedId)
        assertNotNull(insertedCharge)

        chargeDao.delete(insertedCharge)

        val deletedCharge = chargeDao.getChargeById(insertedId)
        assertNull(deletedCharge)
    }

    @Test
    fun getTotalEnergyInDateRange_shouldCalculateCorrectTotal() = runTest {
        val now = Clock.System.now()
        val startDate = now.minus(5, DateTimeUnit.DAY)
        val endDate = now
        
        val charge1 = ChargeSession(
            dateTime = now.minus(3, DateTimeUnit.DAY),
            energyKWh = 30.0,
            unitPrice = 62.0,
            cost = 1860.0
        )
        val charge2 = ChargeSession(
            dateTime = now.minus(2, DateTimeUnit.DAY),
            energyKWh = 40.0,
            unitPrice = 62.0,
            cost = 2480.0
        )

        chargeDao.insert(charge1)
        chargeDao.insert(charge2)

        val totalEnergy = chargeDao.getTotalEnergyInDateRange(startDate, endDate).first()
        assertEquals(70.0, totalEnergy ?: 0.0)
    }

    @Test
    fun getTotalCostInDateRange_shouldCalculateCorrectTotal() = runTest {
        val now = Clock.System.now()
        val startDate = now.minus(5, DateTimeUnit.DAY)
        val endDate = now
        
        val charge1 = ChargeSession(
            dateTime = now.minus(3, DateTimeUnit.DAY),
            energyKWh = 30.0,
            unitPrice = 62.0,
            cost = 1860.0
        )
        val charge2 = ChargeSession(
            dateTime = now.minus(2, DateTimeUnit.DAY),
            energyKWh = 40.0,
            unitPrice = 62.0,
            cost = 2480.0
        )

        chargeDao.insert(charge1)
        chargeDao.insert(charge2)

        val totalCost = chargeDao.getTotalCostInDateRange(startDate, endDate).first()
        assertEquals(4340.0, totalCost ?: 0.0)
    }

    @Test
    fun getDailyChargeStats_shouldReturnCorrectDailyStats() = runTest {
        val now = Clock.System.now()
        val startDate = now.minus(5, DateTimeUnit.DAY)
        val endDate = now
        
        // Same day charges
        val charge1 = ChargeSession(
            dateTime = now.minus(3, DateTimeUnit.DAY),
            energyKWh = 30.0,
            unitPrice = 62.0,
            cost = 1860.0
        )
        val charge2 = ChargeSession(
            dateTime = now.minus(3, DateTimeUnit.DAY),
            energyKWh = 20.0,
            unitPrice = 62.0,
            cost = 1240.0
        )

        chargeDao.insert(charge1)
        chargeDao.insert(charge2)

        val dailyStats = chargeDao.getDailyChargeStats(startDate, endDate).first()
        assertTrue(dailyStats.isNotEmpty())
        
        // Should have aggregated the charges from the same day
        val dayStats = dailyStats.find { 
            it.date.toEpochMilliseconds() == now.minus(3, DateTimeUnit.DAY).toEpochMilliseconds() 
        }
        assertNotNull(dayStats)
        assertEquals(50.0, dayStats.totalEnergy)
        assertEquals(3100.0, dayStats.totalCost)
    }
}
