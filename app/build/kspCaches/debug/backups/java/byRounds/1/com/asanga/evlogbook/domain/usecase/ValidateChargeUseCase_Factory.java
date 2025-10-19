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
public final class ValidateChargeUseCase_Factory implements Factory<ValidateChargeUseCase> {
  private final Provider<CalculateChargeCostUseCase> calculateChargeCostProvider;

  public ValidateChargeUseCase_Factory(
      Provider<CalculateChargeCostUseCase> calculateChargeCostProvider) {
    this.calculateChargeCostProvider = calculateChargeCostProvider;
  }

  @Override
  public ValidateChargeUseCase get() {
    return newInstance(calculateChargeCostProvider.get());
  }

  public static ValidateChargeUseCase_Factory create(
      Provider<CalculateChargeCostUseCase> calculateChargeCostProvider) {
    return new ValidateChargeUseCase_Factory(calculateChargeCostProvider);
  }

  public static ValidateChargeUseCase newInstance(CalculateChargeCostUseCase calculateChargeCost) {
    return new ValidateChargeUseCase(calculateChargeCost);
  }
}
