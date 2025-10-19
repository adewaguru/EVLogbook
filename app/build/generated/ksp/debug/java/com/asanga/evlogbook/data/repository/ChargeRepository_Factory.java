package com.asanga.evlogbook.data.repository;

import com.asanga.evlogbook.data.dao.ChargeDao;
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
public final class ChargeRepository_Factory implements Factory<ChargeRepository> {
  private final Provider<ChargeDao> chargeDaoProvider;

  public ChargeRepository_Factory(Provider<ChargeDao> chargeDaoProvider) {
    this.chargeDaoProvider = chargeDaoProvider;
  }

  @Override
  public ChargeRepository get() {
    return newInstance(chargeDaoProvider.get());
  }

  public static ChargeRepository_Factory create(Provider<ChargeDao> chargeDaoProvider) {
    return new ChargeRepository_Factory(chargeDaoProvider);
  }

  public static ChargeRepository newInstance(ChargeDao chargeDao) {
    return new ChargeRepository(chargeDao);
  }
}
