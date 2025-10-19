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
public final class CalculateTripDistanceUseCase_Factory implements Factory<CalculateTripDistanceUseCase> {
  @Override
  public CalculateTripDistanceUseCase get() {
    return newInstance();
  }

  public static CalculateTripDistanceUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CalculateTripDistanceUseCase newInstance() {
    return new CalculateTripDistanceUseCase();
  }

  private static final class InstanceHolder {
    private static final CalculateTripDistanceUseCase_Factory INSTANCE = new CalculateTripDistanceUseCase_Factory();
  }
}
