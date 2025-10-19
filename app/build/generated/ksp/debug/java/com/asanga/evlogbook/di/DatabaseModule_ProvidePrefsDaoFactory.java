package com.asanga.evlogbook.di;

import com.asanga.evlogbook.data.dao.PrefsDao;
import com.asanga.evlogbook.data.db.AppDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class DatabaseModule_ProvidePrefsDaoFactory implements Factory<PrefsDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvidePrefsDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PrefsDao get() {
    return providePrefsDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvidePrefsDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvidePrefsDaoFactory(databaseProvider);
  }

  public static PrefsDao providePrefsDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePrefsDao(database));
  }
}
