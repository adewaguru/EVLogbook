package com.asanga.evlogbook.ui.screens.charge;

import com.asanga.evlogbook.data.repository.ChargeRepository;
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
public final class ChargeListViewModel_Factory implements Factory<ChargeListViewModel> {
  private final Provider<ChargeRepository> chargeRepositoryProvider;

  public ChargeListViewModel_Factory(Provider<ChargeRepository> chargeRepositoryProvider) {
    this.chargeRepositoryProvider = chargeRepositoryProvider;
  }

  @Override
  public ChargeListViewModel get() {
    return newInstance(chargeRepositoryProvider.get());
  }

  public static ChargeListViewModel_Factory create(
      Provider<ChargeRepository> chargeRepositoryProvider) {
    return new ChargeListViewModel_Factory(chargeRepositoryProvider);
  }

  public static ChargeListViewModel newInstance(ChargeRepository chargeRepository) {
    return new ChargeListViewModel(chargeRepository);
  }
}
