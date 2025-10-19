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
public final class CalculateChargeCostUseCase_Factory implements Factory<CalculateChargeCostUseCase> {
  @Override
  public CalculateChargeCostUseCase get() {
    return newInstance();
  }

  public static CalculateChargeCostUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CalculateChargeCostUseCase newInstance() {
    return new CalculateChargeCostUseCase();
  }

  private static final class InstanceHolder {
    private static final CalculateChargeCostUseCase_Factory INSTANCE = new CalculateChargeCostUseCase_Factory();
  }
}
