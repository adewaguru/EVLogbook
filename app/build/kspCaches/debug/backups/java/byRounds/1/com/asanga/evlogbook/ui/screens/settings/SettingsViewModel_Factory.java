package com.asanga.evlogbook.ui.screens.settings;

import com.asanga.evlogbook.data.repository.PrefsRepository;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<PrefsRepository> prefsRepositoryProvider;

  public SettingsViewModel_Factory(Provider<PrefsRepository> prefsRepositoryProvider) {
    this.prefsRepositoryProvider = prefsRepositoryProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(prefsRepositoryProvider.get());
  }

  public static SettingsViewModel_Factory create(
      Provider<PrefsRepository> prefsRepositoryProvider) {
    return new SettingsViewModel_Factory(prefsRepositoryProvider);
  }

  public static SettingsViewModel newInstance(PrefsRepository prefsRepository) {
    return new SettingsViewModel(prefsRepository);
  }
}
