package com.asanga.evlogbook.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.asanga.evlogbook.data.entity.InstantConverter;
import com.asanga.evlogbook.data.entity.Trip;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import kotlinx.datetime.Instant;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TripDao_Impl implements TripDao {
  private final RoomDatabase __db;

  private final EntityDeletionOrUpdateAdapter<Trip> __deletionAdapterOfTrip;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllTrips;

  private final EntityUpsertionAdapter<Trip> __upsertionAdapterOfTrip;

  private final InstantConverter __instantConverter = new InstantConverter();

  public TripDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__deletionAdapterOfTrip = new EntityDeletionOrUpdateAdapter<Trip>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `trips` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Trip entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllTrips = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM trips";
        return _query;
      }
    };
    this.__upsertionAdapterOfTrip = new EntityUpsertionAdapter<Trip>(new EntityInsertionAdapter<Trip>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `trips` (`id`,`dateTime`,`startOdometerKm`,`endOdometerKm`,`distanceKm`,`energyKWh`,`notes`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Trip entity) {
        statement.bindLong(1, entity.getId());
        final Long _tmp = __instantConverter.fromInstant(entity.getDateTime());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, _tmp);
        }
        statement.bindDouble(3, entity.getStartOdometerKm());
        statement.bindDouble(4, entity.getEndOdometerKm());
        statement.bindDouble(5, entity.getDistanceKm());
        if (entity.getEnergyKWh() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getEnergyKWh());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
      }
    }, new EntityDeletionOrUpdateAdapter<Trip>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `trips` SET `id` = ?,`dateTime` = ?,`startOdometerKm` = ?,`endOdometerKm` = ?,`distanceKm` = ?,`energyKWh` = ?,`notes` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Trip entity) {
        statement.bindLong(1, entity.getId());
        final Long _tmp = __instantConverter.fromInstant(entity.getDateTime());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, _tmp);
        }
        statement.bindDouble(3, entity.getStartOdometerKm());
        statement.bindDouble(4, entity.getEndOdometerKm());
        statement.bindDouble(5, entity.getDistanceKm());
        if (entity.getEnergyKWh() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getEnergyKWh());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
        statement.bindLong(8, entity.getId());
      }
    });
  }

  @Override
  public Object deleteTrip(final Trip trip, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTrip.handle(trip);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllTrips(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllTrips.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllTrips.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertTrip(final Trip trip, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfTrip.upsert(trip);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Trip>> getAllTrips() {
    final String _sql = "SELECT * FROM trips ORDER BY dateTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"trips"}, new Callable<List<Trip>>() {
      @Override
      @NonNull
      public List<Trip> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTime");
          final int _cursorIndexOfStartOdometerKm = CursorUtil.getColumnIndexOrThrow(_cursor, "startOdometerKm");
          final int _cursorIndexOfEndOdometerKm = CursorUtil.getColumnIndexOrThrow(_cursor, "endOdometerKm");
          final int _cursorIndexOfDistanceKm = CursorUtil.getColumnIndexOrThrow(_cursor, "distanceKm");
          final int _cursorIndexOfEnergyKWh = CursorUtil.getColumnIndexOrThrow(_cursor, "energyKWh");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<Trip> _result = new ArrayList<Trip>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Trip _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Instant _tmpDateTime;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDateTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDateTime);
            }
            final Instant _tmp_1 = __instantConverter.toInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpDateTime = _tmp_1;
            }
            final double _tmpStartOdometerKm;
            _tmpStartOdometerKm = _cursor.getDouble(_cursorIndexOfStartOdometerKm);
            final double _tmpEndOdometerKm;
            _tmpEndOdometerKm = _cursor.getDouble(_cursorIndexOfEndOdometerKm);
            final double _tmpDistanceKm;
            _tmpDistanceKm = _cursor.getDouble(_cursorIndexOfDistanceKm);
            final Double _tmpEnergyKWh;
            if (_cursor.isNull(_cursorIndexOfEnergyKWh)) {
              _tmpEnergyKWh = null;
            } else {
              _tmpEnergyKWh = _cursor.getDouble(_cursorIndexOfEnergyKWh);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new Trip(_tmpId,_tmpDateTime,_tmpStartOdometerKm,_tmpEndOdometerKm,_tmpDistanceKm,_tmpEnergyKWh,_tmpNotes);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Trip> getTripById(final long id) {
    final String _sql = "SELECT * FROM trips WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"trips"}, new Callable<Trip>() {
      @Override
      @Nullable
      public Trip call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTime");
          final int _cursorIndexOfStartOdometerKm = CursorUtil.getColumnIndexOrThrow(_cursor, "startOdometerKm");
          final int _cursorIndexOfEndOdometerKm = CursorUtil.getColumnIndexOrThrow(_cursor, "endOdometerKm");
          final int _cursorIndexOfDistanceKm = CursorUtil.getColumnIndexOrThrow(_cursor, "distanceKm");
          final int _cursorIndexOfEnergyKWh = CursorUtil.getColumnIndexOrThrow(_cursor, "energyKWh");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final Trip _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Instant _tmpDateTime;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDateTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDateTime);
            }
            final Instant _tmp_1 = __instantConverter.toInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpDateTime = _tmp_1;
            }
            final double _tmpStartOdometerKm;
            _tmpStartOdometerKm = _cursor.getDouble(_cursorIndexOfStartOdometerKm);
            final double _tmpEndOdometerKm;
            _tmpEndOdometerKm = _cursor.getDouble(_cursorIndexOfEndOdometerKm);
            final double _tmpDistanceKm;
            _tmpDistanceKm = _cursor.getDouble(_cursorIndexOfDistanceKm);
            final Double _tmpEnergyKWh;
            if (_cursor.isNull(_cursorIndexOfEnergyKWh)) {
              _tmpEnergyKWh = null;
            } else {
              _tmpEnergyKWh = _cursor.getDouble(_cursorIndexOfEnergyKWh);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _result = new Trip(_tmpId,_tmpDateTime,_tmpStartOdometerKm,_tmpEndOdometerKm,_tmpDistanceKm,_tmpEnergyKWh,_tmpNotes);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Trip>> getTripsByDateRange(final Instant startDate, final Instant endDate) {
    final String _sql = "SELECT * FROM trips WHERE dateTime BETWEEN ? AND ? ORDER BY dateTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    final Long _tmp = __instantConverter.fromInstant(startDate);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    _argIndex = 2;
    final Long _tmp_1 = __instantConverter.fromInstant(endDate);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_1);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"trips"}, new Callable<List<Trip>>() {
      @Override
      @NonNull
      public List<Trip> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTime");
          final int _cursorIndexOfStartOdometerKm = CursorUtil.getColumnIndexOrThrow(_cursor, "startOdometerKm");
          final int _cursorIndexOfEndOdometerKm = CursorUtil.getColumnIndexOrThrow(_cursor, "endOdometerKm");
          final int _cursorIndexOfDistanceKm = CursorUtil.getColumnIndexOrThrow(_cursor, "distanceKm");
          final int _cursorIndexOfEnergyKWh = CursorUtil.getColumnIndexOrThrow(_cursor, "energyKWh");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<Trip> _result = new ArrayList<Trip>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Trip _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Instant _tmpDateTime;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfDateTime)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfDateTime);
            }
            final Instant _tmp_3 = __instantConverter.toInstant(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpDateTime = _tmp_3;
            }
            final double _tmpStartOdometerKm;
            _tmpStartOdometerKm = _cursor.getDouble(_cursorIndexOfStartOdometerKm);
            final double _tmpEndOdometerKm;
            _tmpEndOdometerKm = _cursor.getDouble(_cursorIndexOfEndOdometerKm);
            final double _tmpDistanceKm;
            _tmpDistanceKm = _cursor.getDouble(_cursorIndexOfDistanceKm);
            final Double _tmpEnergyKWh;
            if (_cursor.isNull(_cursorIndexOfEnergyKWh)) {
              _tmpEnergyKWh = null;
            } else {
              _tmpEnergyKWh = _cursor.getDouble(_cursorIndexOfEnergyKWh);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new Trip(_tmpId,_tmpDateTime,_tmpStartOdometerKm,_tmpEndOdometerKm,_tmpDistanceKm,_tmpEnergyKWh,_tmpNotes);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<MonthlyTripStats> getMonthlyStats(final Instant startOfMonth,
      final Instant startOfNextMonth) {
    final String _sql = "\n"
            + "        SELECT SUM(distanceKm) as totalDistance,\n"
            + "               SUM(energyKWh) as totalEnergy,\n"
            + "               COUNT(*) as tripCount\n"
            + "        FROM trips\n"
            + "        WHERE dateTime >= ? AND dateTime < ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    final Long _tmp = __instantConverter.fromInstant(startOfMonth);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    _argIndex = 2;
    final Long _tmp_1 = __instantConverter.fromInstant(startOfNextMonth);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_1);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"trips"}, new Callable<MonthlyTripStats>() {
      @Override
      @Nullable
      public MonthlyTripStats call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTotalDistance = 0;
          final int _cursorIndexOfTotalEnergy = 1;
          final int _cursorIndexOfTripCount = 2;
          final MonthlyTripStats _result;
          if (_cursor.moveToFirst()) {
            final Double _tmpTotalDistance;
            if (_cursor.isNull(_cursorIndexOfTotalDistance)) {
              _tmpTotalDistance = null;
            } else {
              _tmpTotalDistance = _cursor.getDouble(_cursorIndexOfTotalDistance);
            }
            final Double _tmpTotalEnergy;
            if (_cursor.isNull(_cursorIndexOfTotalEnergy)) {
              _tmpTotalEnergy = null;
            } else {
              _tmpTotalEnergy = _cursor.getDouble(_cursorIndexOfTotalEnergy);
            }
            final Integer _tmpTripCount;
            if (_cursor.isNull(_cursorIndexOfTripCount)) {
              _tmpTripCount = null;
            } else {
              _tmpTripCount = _cursor.getInt(_cursorIndexOfTripCount);
            }
            _result = new MonthlyTripStats(_tmpTotalDistance,_tmpTotalEnergy,_tmpTripCount);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<DailyTripStats>> getDailyTripStats(final Instant startOfMonth,
      final Instant startOfNextMonth, final double efficiency) {
    final String _sql = "\n"
            + "        SELECT DATE(dateTime / 1000, 'unixepoch', 'localtime') as date,\n"
            + "               SUM(distanceKm) as totalDistance,\n"
            + "               SUM(COALESCE(energyKWh, distanceKm * ? / 100.0)) as totalEnergy\n"
            + "        FROM trips\n"
            + "        WHERE dateTime >= ? AND dateTime < ?\n"
            + "        GROUP BY DATE(dateTime / 1000, 'unixepoch', 'localtime')\n"
            + "        ORDER BY date\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindDouble(_argIndex, efficiency);
    _argIndex = 2;
    final Long _tmp = __instantConverter.fromInstant(startOfMonth);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    _argIndex = 3;
    final Long _tmp_1 = __instantConverter.fromInstant(startOfNextMonth);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_1);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"trips"}, new Callable<List<DailyTripStats>>() {
      @Override
      @NonNull
      public List<DailyTripStats> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = 0;
          final int _cursorIndexOfTotalDistance = 1;
          final int _cursorIndexOfTotalEnergy = 2;
          final List<DailyTripStats> _result = new ArrayList<DailyTripStats>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyTripStats _item;
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final Double _tmpTotalDistance;
            if (_cursor.isNull(_cursorIndexOfTotalDistance)) {
              _tmpTotalDistance = null;
            } else {
              _tmpTotalDistance = _cursor.getDouble(_cursorIndexOfTotalDistance);
            }
            final Double _tmpTotalEnergy;
            if (_cursor.isNull(_cursorIndexOfTotalEnergy)) {
              _tmpTotalEnergy = null;
            } else {
              _tmpTotalEnergy = _cursor.getDouble(_cursorIndexOfTotalEnergy);
            }
            _item = new DailyTripStats(_tmpDate,_tmpTotalDistance,_tmpTotalEnergy);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Integer> getTripCount() {
    final String _sql = "SELECT COUNT(*) FROM trips";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"trips"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
