package com.asanga.evlogbook.domain.usecase;

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
public final class ValidateTripUseCase_Factory implements Factory<ValidateTripUseCase> {
  private final Provider<CalculateTripDistanceUseCase> calculateTripDistanceProvider;

  public ValidateTripUseCase_Factory(
      Provider<CalculateTripDistanceUseCase> calculateTripDistanceProvider) {
    this.calculateTripDistanceProvider = calculateTripDistanceProvider;
  }

  @Override
  public ValidateTripUseCase get() {
    return newInstance(calculateTripDistanceProvider.get());
  }

  public static ValidateTripUseCase_Factory create(
      Provider<CalculateTripDistanceUseCase> calculateTripDistanceProvider) {
    return new ValidateTripUseCase_Factory(calculateTripDistanceProvider);
  }

  public static ValidateTripUseCase newInstance(
      CalculateTripDistanceUseCase calculateTripDistance) {
    return new ValidateTripUseCase(calculateTripDistance);
  }
}
