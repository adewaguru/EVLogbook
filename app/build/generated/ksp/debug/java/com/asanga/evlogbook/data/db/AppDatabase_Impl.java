package com.asanga.evlogbook.data.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.asanga.evlogbook.data.dao.ChargeDao;
import com.asanga.evlogbook.data.dao.ChargeDao_Impl;
import com.asanga.evlogbook.data.dao.PrefsDao;
import com.asanga.evlogbook.data.dao.PrefsDao_Impl;
import com.asanga.evlogbook.data.dao.TripDao;
import com.asanga.evlogbook.data.dao.TripDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile TripDao _tripDao;

  private volatile ChargeDao _chargeDao;

  private volatile PrefsDao _prefsDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `trips` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dateTime` INTEGER NOT NULL, `startOdometerKm` REAL NOT NULL, `endOdometerKm` REAL NOT NULL, `distanceKm` REAL NOT NULL, `energyKWh` REAL, `notes` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `charge_sessions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dateTime` INTEGER NOT NULL, `energyKWh` REAL NOT NULL, `unitPrice` REAL NOT NULL, `cost` REAL NOT NULL, `location` TEXT, `notes` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `app_prefs` (`id` INTEGER NOT NULL, `currencyCode` TEXT NOT NULL, `defaultUnitPrice` REAL NOT NULL, `defaultEfficiencyKWhPer100Km` REAL NOT NULL, `themeMode` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '584317e8611b5a2d05a7711198c18244')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `trips`");
        db.execSQL("DROP TABLE IF EXISTS `charge_sessions`");
        db.execSQL("DROP TABLE IF EXISTS `app_prefs`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsTrips = new HashMap<String, TableInfo.Column>(7);
        _columnsTrips.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("dateTime", new TableInfo.Column("dateTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("startOdometerKm", new TableInfo.Column("startOdometerKm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("endOdometerKm", new TableInfo.Column("endOdometerKm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("distanceKm", new TableInfo.Column("distanceKm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("energyKWh", new TableInfo.Column("energyKWh", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrips = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTrips = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTrips = new TableInfo("trips", _columnsTrips, _foreignKeysTrips, _indicesTrips);
        final TableInfo _existingTrips = TableInfo.read(db, "trips");
        if (!_infoTrips.equals(_existingTrips)) {
          return new RoomOpenHelper.ValidationResult(false, "trips(com.asanga.evlogbook.data.entity.Trip).\n"
                  + " Expected:\n" + _infoTrips + "\n"
                  + " Found:\n" + _existingTrips);
        }
        final HashMap<String, TableInfo.Column> _columnsChargeSessions = new HashMap<String, TableInfo.Column>(7);
        _columnsChargeSessions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChargeSessions.put("dateTime", new TableInfo.Column("dateTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChargeSessions.put("energyKWh", new TableInfo.Column("energyKWh", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChargeSessions.put("unitPrice", new TableInfo.Column("unitPrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChargeSessions.put("cost", new TableInfo.Column("cost", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChargeSessions.put("location", new TableInfo.Column("location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChargeSessions.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysChargeSessions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesChargeSessions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoChargeSessions = new TableInfo("charge_sessions", _columnsChargeSessions, _foreignKeysChargeSessions, _indicesChargeSessions);
        final TableInfo _existingChargeSessions = TableInfo.read(db, "charge_sessions");
        if (!_infoChargeSessions.equals(_existingChargeSessions)) {
          return new RoomOpenHelper.ValidationResult(false, "charge_sessions(com.asanga.evlogbook.data.entity.ChargeSession).\n"
                  + " Expected:\n" + _infoChargeSessions + "\n"
                  + " Found:\n" + _existingChargeSessions);
        }
        final HashMap<String, TableInfo.Column> _columnsAppPrefs = new HashMap<String, TableInfo.Column>(5);
        _columnsAppPrefs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPrefs.put("currencyCode", new TableInfo.Column("currencyCode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPrefs.put("defaultUnitPrice", new TableInfo.Column("defaultUnitPrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPrefs.put("defaultEfficiencyKWhPer100Km", new TableInfo.Column("defaultEfficiencyKWhPer100Km", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPrefs.put("themeMode", new TableInfo.Column("themeMode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppPrefs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppPrefs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppPrefs = new TableInfo("app_prefs", _columnsAppPrefs, _foreignKeysAppPrefs, _indicesAppPrefs);
        final TableInfo _existingAppPrefs = TableInfo.read(db, "app_prefs");
        if (!_infoAppPrefs.equals(_existingAppPrefs)) {
          return new RoomOpenHelper.ValidationResult(false, "app_prefs(com.asanga.evlogbook.data.entity.AppPrefs).\n"
                  + " Expected:\n" + _infoAppPrefs + "\n"
                  + " Found:\n" + _existingAppPrefs);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "584317e8611b5a2d05a7711198c18244", "fb810e28367a16756e41f4501ee58a99");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "trips","charge_sessions","app_prefs");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `trips`");
      _db.execSQL("DELETE FROM `charge_sessions`");
      _db.execSQL("DELETE FROM `app_prefs`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(TripDao.class, TripDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ChargeDao.class, ChargeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PrefsDao.class, PrefsDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public TripDao tripDao() {
    if (_tripDao != null) {
      return _tripDao;
    } else {
      synchronized(this) {
        if(_tripDao == null) {
          _tripDao = new TripDao_Impl(this);
        }
        return _tripDao;
      }
    }
  }

  @Override
  public ChargeDao chargeDao() {
    if (_chargeDao != null) {
      return _chargeDao;
    } else {
      synchronized(this) {
        if(_chargeDao == null) {
          _chargeDao = new ChargeDao_Impl(this);
        }
        return _chargeDao;
      }
    }
  }

  @Override
  public PrefsDao prefsDao() {
    if (_prefsDao != null) {
      return _prefsDao;
    } else {
      synchronized(this) {
        if(_prefsDao == null) {
          _prefsDao = new PrefsDao_Impl(this);
        }
        return _prefsDao;
      }
    }
  }
}
