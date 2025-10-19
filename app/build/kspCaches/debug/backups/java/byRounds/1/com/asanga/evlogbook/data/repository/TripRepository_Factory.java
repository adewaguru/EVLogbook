package com.asanga.evlogbook.data.repository;

import com.asanga.evlogbook.data.dao.TripDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class TripRepository_Factory implements Factory<TripRepository> {
  private final Provider<TripDao> tripDaoProvider;

  public TripRepository_Factory(Provider<TripDao> tripDaoProvider) {
    this.tripDaoProvider = tripDaoProvider;
  }

  @Override
  public TripRepository get() {
    return newInstance(tripDaoProvider.get());
  }

  public static TripRepository_Factory create(Provider<TripDao> tripDaoProvider) {
    return new TripRepository_Factory(tripDaoProvider);
  }

  public static TripRepository newInstance(TripDao tripDao) {
    return new TripRepository(tripDao);
  }
}
