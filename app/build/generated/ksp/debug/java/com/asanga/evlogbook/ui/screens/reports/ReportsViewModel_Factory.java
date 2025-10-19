package com.asanga.evlogbook.ui.screens.reports;

import com.asanga.evlogbook.data.repository.ChargeRepository;
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
public final class ReportsViewModel_Factory implements Factory<ReportsViewModel> {
  private final Provider<TripRepository> tripRepositoryProvider;

  private final Provider<ChargeRepository> chargeRepositoryProvider;

  public ReportsViewModel_Factory(Provider<TripRepository> tripRepositoryProvider,
      Provider<ChargeRepository> chargeRepositoryProvider) {
    this.tripRepositoryProvider = tripRepositoryProvider;
    this.chargeRepositoryProvider = chargeRepositoryProvider;
  }

  @Override
  public ReportsViewModel get() {
    return newInstance(tripRepositoryProvider.get(), chargeRepositoryProvider.get());
  }

  public static ReportsViewModel_Factory create(Provider<TripRepository> tripRepositoryProvider,
      Provider<ChargeRepository> chargeRepositoryProvider) {
    return new ReportsViewModel_Factory(tripRepositoryProvider, chargeRepositoryProvider);
  }

  public static ReportsViewModel newInstance(TripRepository tripRepository,
      ChargeRepository chargeRepository) {
    return new ReportsViewModel(tripRepository, chargeRepository);
  }
}
