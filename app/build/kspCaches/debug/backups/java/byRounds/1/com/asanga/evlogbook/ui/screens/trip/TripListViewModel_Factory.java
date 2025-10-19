package com.asanga.evlogbook.ui.screens.trip;

import com.asanga.evlogbook.data.repository.TripRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class TripListViewModel_Factory implements Factory<TripListViewModel> {
  private final Provider<TripRepository> tripRepositoryProvider;

  public TripListViewModel_Factory(Provider<TripRepository> tripRepositoryProvider) {
    this.tripRepositoryProvider = tripRepositoryProvider;
  }

  @Override
  public TripListViewModel get() {
    return newInstance(tripRepositoryProvider.get());
  }

  public static TripListViewModel_Factory create(Provider<TripRepository> tripRepositoryProvider) {
    return new TripListViewModel_Factory(tripRepositoryProvider);
  }

  public static TripListViewModel newInstance(TripRepository tripRepository) {
    return new TripListViewModel(tripRepository);
  }
}
