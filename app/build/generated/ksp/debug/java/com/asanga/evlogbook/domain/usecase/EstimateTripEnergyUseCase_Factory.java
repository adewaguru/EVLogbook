package com.asanga.evlogbook.domain.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class EstimateTripEnergyUseCase_Factory implements Factory<EstimateTripEnergyUseCase> {
  @Override
  public EstimateTripEnergyUseCase get() {
    return newInstance();
  }

  public static EstimateTripEnergyUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static EstimateTripEnergyUseCase newInstance() {
    return new EstimateTripEnergyUseCase();
  }

  private static final class InstanceHolder {
    private static final EstimateTripEnergyUseCase_Factory INSTANCE = new EstimateTripEnergyUseCase_Factory();
  }
}
