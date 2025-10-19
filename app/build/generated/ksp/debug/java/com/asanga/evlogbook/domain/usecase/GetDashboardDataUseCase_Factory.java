package com.asanga.evlogbook.domain.usecase;

import com.asanga.evlogbook.data.repository.ChargeRepository;
import com.asanga.evlogbook.data.repository.PrefsRepository;
import com.asanga.evlogbook.data.repository.TripRepository;
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
public final class GetDashboardDataUseCase_Factory implements Factory<GetDashboardDataUseCase> {
  private final Provider<TripRepository> tripRepositoryProvider;

  private final Provider<ChargeRepository> chargeRepositoryProvider;

  private final Provider<PrefsRepository> prefsRepositoryProvider;

  public GetDashboardDataUseCase_Factory(Provider<TripRepository> tripRepositoryProvider,
      Provider<ChargeRepository> chargeRepositoryProvider,
      Provider<PrefsRepository> prefsRepositoryProvider) {
    this.tripRepositoryProvider = tripRepositoryProvider;
    this.chargeRepositoryProvider = chargeRepositoryProvider;
    this.prefsRepositoryProvider = prefsRepositoryProvider;
  }

  @Override
  public GetDashboardDataUseCase get() {
    return newInstance(tripRepositoryProvider.get(), chargeRepositoryProvider.get(), prefsRepositoryProvider.get());
  }

  public static GetDashboardDataUseCase_Factory create(
      Provider<TripRepository> tripRepositoryProvider,
      Provider<ChargeRepository> chargeRepositoryProvider,
      Provider<PrefsRepository> prefsRepositoryProvider) {
    return new GetDashboardDataUseCase_Factory(tripRepositoryProvider, chargeRepositoryProvider, prefsRepositoryProvider);
  }

  public static GetDashboardDataUseCase newInstance(TripRepository tripRepository,
      ChargeRepository chargeRepository, PrefsRepository prefsRepository) {
    return new GetDashboardDataUseCase(tripRepository, chargeRepository, prefsRepository);
  }
}
