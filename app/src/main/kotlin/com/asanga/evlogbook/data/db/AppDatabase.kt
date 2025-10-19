package com.asanga.evlogbook.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.asanga.evlogbook.data.dao.ChargeDao
import com.asanga.evlogbook.data.dao.PrefsDao
import com.asanga.evlogbook.data.dao.TripDao
import com.asanga.evlogbook.data.entity.AppPrefs
import com.asanga.evlogbook.data.entity.ChargeSession
import com.asanga.evlogbook.data.entity.InstantConverter
import com.asanga.evlogbook.data.entity.Trip
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Trip::class, ChargeSession::class, AppPrefs::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(InstantConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tripDao(): TripDao
    abstract fun chargeDao(): ChargeDao
    abstract fun prefsDao(): PrefsDao

    companion object {
        const val DATABASE_NAME = "ev_logbook_db"
    }
}

class DatabaseCallback : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        // Initialize default preferences
        CoroutineScope(Dispatchers.IO).launch {
            // This will be handled by the repository with proper dependency injection
        }
    }
}
