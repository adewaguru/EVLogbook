package com.asanga.evlogbook.data.repository;

import com.asanga.evlogbook.data.dao.PrefsDao;
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
public final class PrefsRepository_Factory implements Factory<PrefsRepository> {
  private final Provider<PrefsDao> prefsDaoProvider;

  public PrefsRepository_Factory(Provider<PrefsDao> prefsDaoProvider) {
    this.prefsDaoProvider = prefsDaoProvider;
  }

  @Override
  public PrefsRepository get() {
    return newInstance(prefsDaoProvider.get());
  }

  public static PrefsRepository_Factory create(Provider<PrefsDao> prefsDaoProvider) {
    return new PrefsRepository_Factory(prefsDaoProvider);
  }

  public static PrefsRepository newInstance(PrefsDao prefsDao) {
    return new PrefsRepository(prefsDao);
  }
}
