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
public final class AddEditTripViewModel_Factory implements Factory<AddEditTripViewModel> {
  private final Provider<TripRepository> tripRepositoryProvider;

  public AddEditTripViewModel_Factory(Provider<TripRepository> tripRepositoryProvider) {
    this.tripRepositoryProvider = tripRepositoryProvider;
  }

  @Override
  public AddEditTripViewModel get() {
    return newInstance(tripRepositoryProvider.get());
  }

  public static AddEditTripViewModel_Factory create(
      Provider<TripRepository> tripRepositoryProvider) {
    return new AddEditTripViewModel_Factory(tripRepositoryProvider);
  }

  public static AddEditTripViewModel newInstance(TripRepository tripRepository) {
    return new AddEditTripViewModel(tripRepository);
  }
}
