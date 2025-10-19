package com.asanga.evlogbook.di

import android.content.Context
import androidx.room.Room
import com.asanga.evlogbook.data.dao.ChargeDao
import com.asanga.evlogbook.data.dao.PrefsDao
import com.asanga.evlogbook.data.dao.TripDao
import com.asanga.evlogbook.data.db.AppDatabase
import com.asanga.evlogbook.data.db.DatabaseCallback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        callback: DatabaseCallback
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
        .addCallback(callback)
        .fallbackToDestructiveMigration() // For simplicity in MVP
        .build()
    }

    @Provides
    @Singleton
    fun provideTripDao(database: AppDatabase): TripDao = database.tripDao()

    @Provides
    @Singleton
    fun provideChargeDao(database: AppDatabase): ChargeDao = database.chargeDao()

    @Provides
    @Singleton
    fun providePrefsDao(database: AppDatabase): PrefsDao = database.prefsDao()

    @Provides
    @Singleton
    fun provideDatabaseCallback(): DatabaseCallback = DatabaseCallback()
}
