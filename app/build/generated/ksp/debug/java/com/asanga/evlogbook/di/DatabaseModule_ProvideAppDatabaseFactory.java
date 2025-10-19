package com.asanga.evlogbook.di;

import android.content.Context;
import com.asanga.evlogbook.data.db.AppDatabase;
import com.asanga.evlogbook.data.db.DatabaseCallback;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DatabaseModule_ProvideAppDatabaseFactory implements Factory<AppDatabase> {
  private final Provider<Context> contextProvider;

  private final Provider<DatabaseCallback> callbackProvider;

  public DatabaseModule_ProvideAppDatabaseFactory(Provider<Context> contextProvider,
      Provider<DatabaseCallback> callbackProvider) {
    this.contextProvider = contextProvider;
    this.callbackProvider = callbackProvider;
  }

  @Override
  public AppDatabase get() {
    return provideAppDatabase(contextProvider.get(), callbackProvider.get());
  }

  public static DatabaseModule_ProvideAppDatabaseFactory create(Provider<Context> contextProvider,
      Provider<DatabaseCallback> callbackProvider) {
    return new DatabaseModule_ProvideAppDatabaseFactory(contextProvider, callbackProvider);
  }

  public static AppDatabase provideAppDatabase(Context context, DatabaseCallback callback) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideAppDatabase(context, callback));
  }
}
