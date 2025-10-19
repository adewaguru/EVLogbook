package com.asanga.evlogbook.di;

import com.asanga.evlogbook.data.dao.TripDao;
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
public final class DatabaseModule_ProvideTripDaoFactory implements Factory<TripDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideTripDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public TripDao get() {
    return provideTripDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideTripDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideTripDaoFactory(databaseProvider);
  }

  public static TripDao provideTripDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideTripDao(database));
  }
}
