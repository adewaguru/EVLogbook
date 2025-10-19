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
import com.asanga.evlogbook.data.entity.ChargeSession;
import com.asanga.evlogbook.data.entity.InstantConverter;
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
public final class ChargeDao_Impl implements ChargeDao {
  private final RoomDatabase __db;

  private final EntityDeletionOrUpdateAdapter<ChargeSession> __deletionAdapterOfChargeSession;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllCharges;

  private final EntityUpsertionAdapter<ChargeSession> __upsertionAdapterOfChargeSession;

  private final InstantConverter __instantConverter = new InstantConverter();

  public ChargeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__deletionAdapterOfChargeSession = new EntityDeletionOrUpdateAdapter<ChargeSession>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `charge_sessions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ChargeSession entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllCharges = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM charge_sessions";
        return _query;
      }
    };
    this.__upsertionAdapterOfChargeSession = new EntityUpsertionAdapter<ChargeSession>(new EntityInsertionAdapter<ChargeSession>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `charge_sessions` (`id`,`dateTime`,`energyKWh`,`unitPrice`,`cost`,`location`,`notes`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ChargeSession entity) {
        statement.bindLong(1, entity.getId());
        final Long _tmp = __instantConverter.fromInstant(entity.getDateTime());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, _tmp);
        }
        statement.bindDouble(3, entity.getEnergyKWh());
        statement.bindDouble(4, entity.getUnitPrice());
        statement.bindDouble(5, entity.getCost());
        if (entity.getLocation() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getLocation());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
      }
    }, new EntityDeletionOrUpdateAdapter<ChargeSession>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `charge_sessions` SET `id` = ?,`dateTime` = ?,`energyKWh` = ?,`unitPrice` = ?,`cost` = ?,`location` = ?,`notes` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ChargeSession entity) {
        statement.bindLong(1, entity.getId());
        final Long _tmp = __instantConverter.fromInstant(entity.getDateTime());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, _tmp);
        }
        statement.bindDouble(3, entity.getEnergyKWh());
        statement.bindDouble(4, entity.getUnitPrice());
        statement.bindDouble(5, entity.getCost());
        if (entity.getLocation() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getLocation());
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
  public Object deleteCharge(final ChargeSession charge,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfChargeSession.handle(charge);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllCharges(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllCharges.acquire();
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
          __preparedStmtOfDeleteAllCharges.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertCharge(final ChargeSession charge,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfChargeSession.upsert(charge);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ChargeSession>> getAllCharges() {
    final String _sql = "SELECT * FROM charge_sessions ORDER BY dateTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"charge_sessions"}, new Callable<List<ChargeSession>>() {
      @Override
      @NonNull
      public List<ChargeSession> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTime");
          final int _cursorIndexOfEnergyKWh = CursorUtil.getColumnIndexOrThrow(_cursor, "energyKWh");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unitPrice");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<ChargeSession> _result = new ArrayList<ChargeSession>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ChargeSession _item;
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
            final double _tmpEnergyKWh;
            _tmpEnergyKWh = _cursor.getDouble(_cursorIndexOfEnergyKWh);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpCost;
            _tmpCost = _cursor.getDouble(_cursorIndexOfCost);
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new ChargeSession(_tmpId,_tmpDateTime,_tmpEnergyKWh,_tmpUnitPrice,_tmpCost,_tmpLocation,_tmpNotes);
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
  public Flow<ChargeSession> getChargeById(final long id) {
    final String _sql = "SELECT * FROM charge_sessions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"charge_sessions"}, new Callable<ChargeSession>() {
      @Override
      @Nullable
      public ChargeSession call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTime");
          final int _cursorIndexOfEnergyKWh = CursorUtil.getColumnIndexOrThrow(_cursor, "energyKWh");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unitPrice");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final ChargeSession _result;
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
            final double _tmpEnergyKWh;
            _tmpEnergyKWh = _cursor.getDouble(_cursorIndexOfEnergyKWh);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpCost;
            _tmpCost = _cursor.getDouble(_cursorIndexOfCost);
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _result = new ChargeSession(_tmpId,_tmpDateTime,_tmpEnergyKWh,_tmpUnitPrice,_tmpCost,_tmpLocation,_tmpNotes);
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
  public Flow<List<ChargeSession>> getChargesByDateRange(final Instant startDate,
      final Instant endDate) {
    final String _sql = "SELECT * FROM charge_sessions WHERE dateTime BETWEEN ? AND ? ORDER BY dateTime DESC";
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
    return CoroutinesRoom.createFlow(__db, false, new String[] {"charge_sessions"}, new Callable<List<ChargeSession>>() {
      @Override
      @NonNull
      public List<ChargeSession> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTime");
          final int _cursorIndexOfEnergyKWh = CursorUtil.getColumnIndexOrThrow(_cursor, "energyKWh");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unitPrice");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<ChargeSession> _result = new ArrayList<ChargeSession>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ChargeSession _item;
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
            final double _tmpEnergyKWh;
            _tmpEnergyKWh = _cursor.getDouble(_cursorIndexOfEnergyKWh);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpCost;
            _tmpCost = _cursor.getDouble(_cursorIndexOfCost);
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new ChargeSession(_tmpId,_tmpDateTime,_tmpEnergyKWh,_tmpUnitPrice,_tmpCost,_tmpLocation,_tmpNotes);
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
  public Flow<MonthlyChargeStats> getMonthlyChargeStats(final Instant startOfMonth,
      final Instant startOfNextMonth) {
    final String _sql = "\n"
            + "        SELECT SUM(energyKWh) as totalEnergy,\n"
            + "               SUM(cost) as totalCost,\n"
            + "               COUNT(*) as chargeCount\n"
            + "        FROM charge_sessions\n"
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
    return CoroutinesRoom.createFlow(__db, false, new String[] {"charge_sessions"}, new Callable<MonthlyChargeStats>() {
      @Override
      @Nullable
      public MonthlyChargeStats call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTotalEnergy = 0;
          final int _cursorIndexOfTotalCost = 1;
          final int _cursorIndexOfChargeCount = 2;
          final MonthlyChargeStats _result;
          if (_cursor.moveToFirst()) {
            final Double _tmpTotalEnergy;
            if (_cursor.isNull(_cursorIndexOfTotalEnergy)) {
              _tmpTotalEnergy = null;
            } else {
              _tmpTotalEnergy = _cursor.getDouble(_cursorIndexOfTotalEnergy);
            }
            final Double _tmpTotalCost;
            if (_cursor.isNull(_cursorIndexOfTotalCost)) {
              _tmpTotalCost = null;
            } else {
              _tmpTotalCost = _cursor.getDouble(_cursorIndexOfTotalCost);
            }
            final Integer _tmpChargeCount;
            if (_cursor.isNull(_cursorIndexOfChargeCount)) {
              _tmpChargeCount = null;
            } else {
              _tmpChargeCount = _cursor.getInt(_cursorIndexOfChargeCount);
            }
            _result = new MonthlyChargeStats(_tmpTotalEnergy,_tmpTotalCost,_tmpChargeCount);
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
  public Flow<List<DailyChargeStats>> getDailyChargeStats(final Instant startOfMonth,
      final Instant startOfNextMonth) {
    final String _sql = "\n"
            + "        SELECT DATE(dateTime / 1000, 'unixepoch', 'localtime') as date,\n"
            + "               SUM(energyKWh) as totalEnergy,\n"
            + "               SUM(cost) as totalCost\n"
            + "        FROM charge_sessions\n"
            + "        WHERE dateTime >= ? AND dateTime < ?\n"
            + "        GROUP BY DATE(dateTime / 1000, 'unixepoch', 'localtime')\n"
            + "        ORDER BY date\n"
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
    return CoroutinesRoom.createFlow(__db, false, new String[] {"charge_sessions"}, new Callable<List<DailyChargeStats>>() {
      @Override
      @NonNull
      public List<DailyChargeStats> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = 0;
          final int _cursorIndexOfTotalEnergy = 1;
          final int _cursorIndexOfTotalCost = 2;
          final List<DailyChargeStats> _result = new ArrayList<DailyChargeStats>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyChargeStats _item;
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final Double _tmpTotalEnergy;
            if (_cursor.isNull(_cursorIndexOfTotalEnergy)) {
              _tmpTotalEnergy = null;
            } else {
              _tmpTotalEnergy = _cursor.getDouble(_cursorIndexOfTotalEnergy);
            }
            final Double _tmpTotalCost;
            if (_cursor.isNull(_cursorIndexOfTotalCost)) {
              _tmpTotalCost = null;
            } else {
              _tmpTotalCost = _cursor.getDouble(_cursorIndexOfTotalCost);
            }
            _item = new DailyChargeStats(_tmpDate,_tmpTotalEnergy,_tmpTotalCost);
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
  public Flow<Integer> getChargeCount() {
    final String _sql = "SELECT COUNT(*) FROM charge_sessions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"charge_sessions"}, new Callable<Integer>() {
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
