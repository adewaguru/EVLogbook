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
public final class AddEditChargeViewModel_Factory implements Factory<AddEditChargeViewModel> {
  private final Provider<ChargeRepository> chargeRepositoryProvider;

  public AddEditChargeViewModel_Factory(Provider<ChargeRepository> chargeRepositoryProvider) {
    this.chargeRepositoryProvider = chargeRepositoryProvider;
  }

  @Override
  public AddEditChargeViewModel get() {
    return newInstance(chargeRepositoryProvider.get());
  }

  public static AddEditChargeViewModel_Factory create(
      Provider<ChargeRepository> chargeRepositoryProvider) {
    return new AddEditChargeViewModel_Factory(chargeRepositoryProvider);
  }

  public static AddEditChargeViewModel newInstance(ChargeRepository chargeRepository) {
    return new AddEditChargeViewModel(chargeRepository);
  }
}
