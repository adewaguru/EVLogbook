package com.asanga.evlogbook.di;

import com.asanga.evlogbook.data.dao.ChargeDao;
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
public final class DatabaseModule_ProvideChargeDaoFactory implements Factory<ChargeDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideChargeDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ChargeDao get() {
    return provideChargeDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideChargeDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideChargeDaoFactory(databaseProvider);
  }

  public static ChargeDao provideChargeDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideChargeDao(database));
  }
}
