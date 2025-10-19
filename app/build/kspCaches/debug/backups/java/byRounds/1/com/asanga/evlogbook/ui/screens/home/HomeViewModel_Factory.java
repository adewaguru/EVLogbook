package com.asanga.evlogbook.ui.screens.home;

import com.asanga.evlogbook.domain.usecase.GetDashboardDataUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetDashboardDataUseCase> getDashboardDataUseCaseProvider;

  public HomeViewModel_Factory(Provider<GetDashboardDataUseCase> getDashboardDataUseCaseProvider) {
    this.getDashboardDataUseCaseProvider = getDashboardDataUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getDashboardDataUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetDashboardDataUseCase> getDashboardDataUseCaseProvider) {
    return new HomeViewModel_Factory(getDashboardDataUseCaseProvider);
  }

  public static HomeViewModel newInstance(GetDashboardDataUseCase getDashboardDataUseCase) {
    return new HomeViewModel(getDashboardDataUseCase);
  }
}
