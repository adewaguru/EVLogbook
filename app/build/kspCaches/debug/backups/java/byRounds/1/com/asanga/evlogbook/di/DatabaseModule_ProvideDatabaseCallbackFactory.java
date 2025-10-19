package com.asanga.evlogbook.di;

import com.asanga.evlogbook.data.db.DatabaseCallback;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideDatabaseCallbackFactory implements Factory<DatabaseCallback> {
  @Override
  public DatabaseCallback get() {
    return provideDatabaseCallback();
  }

  public static DatabaseModule_ProvideDatabaseCallbackFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DatabaseCallback provideDatabaseCallback() {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDatabaseCallback());
  }

  private static final class InstanceHolder {
    private static final DatabaseModule_ProvideDatabaseCallbackFactory INSTANCE = new DatabaseModule_ProvideDatabaseCallbackFactory();
  }
}
