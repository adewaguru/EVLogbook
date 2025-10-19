package com.asanga.evlogbook.domain.usecase;

import com.asanga.evlogbook.data.repository.ChargeRepository;
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
public final class ImportDataUseCase_Factory implements Factory<ImportDataUseCase> {
  private final Provider<TripRepository> tripRepositoryProvider;

  private final Provider<ChargeRepository> chargeRepositoryProvider;

  public ImportDataUseCase_Factory(Provider<TripRepository> tripRepositoryProvider,
      Provider<ChargeRepository> chargeRepositoryProvider) {
    this.tripRepositoryProvider = tripRepositoryProvider;
    this.chargeRepositoryProvider = chargeRepositoryProvider;
  }

  @Override
  public ImportDataUseCase get() {
    return newInstance(tripRepositoryProvider.get(), chargeRepositoryProvider.get());
  }

  public static ImportDataUseCase_Factory create(Provider<TripRepository> tripRepositoryProvider,
      Provider<ChargeRepository> chargeRepositoryProvider) {
    return new ImportDataUseCase_Factory(tripRepositoryProvider, chargeRepositoryProvider);
  }

  public static ImportDataUseCase newInstance(TripRepository tripRepository,
      ChargeRepository chargeRepository) {
    return new ImportDataUseCase(tripRepository, chargeRepository);
  }
}
