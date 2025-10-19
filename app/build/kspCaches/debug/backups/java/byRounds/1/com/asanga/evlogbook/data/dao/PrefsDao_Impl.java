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
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.asanga.evlogbook.data.entity.AppPrefs;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PrefsDao_Impl implements PrefsDao {
  private final RoomDatabase __db;

  private final EntityUpsertionAdapter<AppPrefs> __upsertionAdapterOfAppPrefs;

  public PrefsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__upsertionAdapterOfAppPrefs = new EntityUpsertionAdapter<AppPrefs>(new EntityInsertionAdapter<AppPrefs>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `app_prefs` (`id`,`currencyCode`,`defaultUnitPrice`,`defaultEfficiencyKWhPer100Km`,`themeMode`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppPrefs entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getCurrencyCode());
        statement.bindDouble(3, entity.getDefaultUnitPrice());
        statement.bindDouble(4, entity.getDefaultEfficiencyKWhPer100Km());
        statement.bindString(5, entity.getThemeMode());
      }
    }, new EntityDeletionOrUpdateAdapter<AppPrefs>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `app_prefs` SET `id` = ?,`currencyCode` = ?,`defaultUnitPrice` = ?,`defaultEfficiencyKWhPer100Km` = ?,`themeMode` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppPrefs entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getCurrencyCode());
        statement.bindDouble(3, entity.getDefaultUnitPrice());
        statement.bindDouble(4, entity.getDefaultEfficiencyKWhPer100Km());
        statement.bindString(5, entity.getThemeMode());
        statement.bindLong(6, entity.getId());
      }
    });
  }

  @Override
  public Object upsertPrefs(final AppPrefs prefs, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfAppPrefs.upsert(prefs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<AppPrefs> getPrefs() {
    final String _sql = "SELECT * FROM app_prefs WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"app_prefs"}, new Callable<AppPrefs>() {
      @Override
      @Nullable
      public AppPrefs call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCurrencyCode = CursorUtil.getColumnIndexOrThrow(_cursor, "currencyCode");
          final int _cursorIndexOfDefaultUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultUnitPrice");
          final int _cursorIndexOfDefaultEfficiencyKWhPer100Km = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultEfficiencyKWhPer100Km");
          final int _cursorIndexOfThemeMode = CursorUtil.getColumnIndexOrThrow(_cursor, "themeMode");
          final AppPrefs _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpCurrencyCode;
            _tmpCurrencyCode = _cursor.getString(_cursorIndexOfCurrencyCode);
            final double _tmpDefaultUnitPrice;
            _tmpDefaultUnitPrice = _cursor.getDouble(_cursorIndexOfDefaultUnitPrice);
            final double _tmpDefaultEfficiencyKWhPer100Km;
            _tmpDefaultEfficiencyKWhPer100Km = _cursor.getDouble(_cursorIndexOfDefaultEfficiencyKWhPer100Km);
            final String _tmpThemeMode;
            _tmpThemeMode = _cursor.getString(_cursorIndexOfThemeMode);
            _result = new AppPrefs(_tmpId,_tmpCurrencyCode,_tmpDefaultUnitPrice,_tmpDefaultEfficiencyKWhPer100Km,_tmpThemeMode);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
