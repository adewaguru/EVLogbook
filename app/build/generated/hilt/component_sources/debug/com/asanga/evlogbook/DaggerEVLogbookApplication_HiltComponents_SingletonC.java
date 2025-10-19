package com.asanga.evlogbook;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.asanga.evlogbook.data.dao.ChargeDao;
import com.asanga.evlogbook.data.dao.PrefsDao;
import com.asanga.evlogbook.data.dao.TripDao;
import com.asanga.evlogbook.data.db.AppDatabase;
import com.asanga.evlogbook.data.db.DatabaseCallback;
import com.asanga.evlogbook.data.repository.ChargeRepository;
import com.asanga.evlogbook.data.repository.PrefsRepository;
import com.asanga.evlogbook.data.repository.TripRepository;
import com.asanga.evlogbook.di.DatabaseModule_ProvideAppDatabaseFactory;
import com.asanga.evlogbook.di.DatabaseModule_ProvideChargeDaoFactory;
import com.asanga.evlogbook.di.DatabaseModule_ProvideDatabaseCallbackFactory;
import com.asanga.evlogbook.di.DatabaseModule_ProvidePrefsDaoFactory;
import com.asanga.evlogbook.di.DatabaseModule_ProvideTripDaoFactory;
import com.asanga.evlogbook.domain.usecase.GetDashboardDataUseCase;
import com.asanga.evlogbook.ui.MainActivity;
import com.asanga.evlogbook.ui.screens.charge.AddEditChargeViewModel;
import com.asanga.evlogbook.ui.screens.charge.AddEditChargeViewModel_HiltModules;
import com.asanga.evlogbook.ui.screens.charge.ChargeListViewModel;
import com.asanga.evlogbook.ui.screens.charge.ChargeListViewModel_HiltModules;
import com.asanga.evlogbook.ui.screens.home.HomeViewModel;
import com.asanga.evlogbook.ui.screens.home.HomeViewModel_HiltModules;
import com.asanga.evlogbook.ui.screens.reports.ReportsViewModel;
import com.asanga.evlogbook.ui.screens.reports.ReportsViewModel_HiltModules;
import com.asanga.evlogbook.ui.screens.settings.SettingsViewModel;
import com.asanga.evlogbook.ui.screens.settings.SettingsViewModel_HiltModules;
import com.asanga.evlogbook.ui.screens.trip.AddEditTripViewModel;
import com.asanga.evlogbook.ui.screens.trip.AddEditTripViewModel_HiltModules;
import com.asanga.evlogbook.ui.screens.trip.TripListViewModel;
import com.asanga.evlogbook.ui.screens.trip.TripListViewModel_HiltModules;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerEVLogbookApplication_HiltComponents_SingletonC {
  private DaggerEVLogbookApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public EVLogbookApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements EVLogbookApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public EVLogbookApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements EVLogbookApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public EVLogbookApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements EVLogbookApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public EVLogbookApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements EVLogbookApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public EVLogbookApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements EVLogbookApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public EVLogbookApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements EVLogbookApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public EVLogbookApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements EVLogbookApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public EVLogbookApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends EVLogbookApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends EVLogbookApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends EVLogbookApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends EVLogbookApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(7).put(LazyClassKeyProvider.com_asanga_evlogbook_ui_screens_charge_AddEditChargeViewModel, AddEditChargeViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_asanga_evlogbook_ui_screens_trip_AddEditTripViewModel, AddEditTripViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_asanga_evlogbook_ui_screens_charge_ChargeListViewModel, ChargeListViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_asanga_evlogbook_ui_screens_home_HomeViewModel, HomeViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_asanga_evlogbook_ui_screens_reports_ReportsViewModel, ReportsViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_asanga_evlogbook_ui_screens_settings_SettingsViewModel, SettingsViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_asanga_evlogbook_ui_screens_trip_TripListViewModel, TripListViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_asanga_evlogbook_ui_screens_charge_ChargeListViewModel = "com.asanga.evlogbook.ui.screens.charge.ChargeListViewModel";

      static String com_asanga_evlogbook_ui_screens_home_HomeViewModel = "com.asanga.evlogbook.ui.screens.home.HomeViewModel";

      static String com_asanga_evlogbook_ui_screens_trip_AddEditTripViewModel = "com.asanga.evlogbook.ui.screens.trip.AddEditTripViewModel";

      static String com_asanga_evlogbook_ui_screens_settings_SettingsViewModel = "com.asanga.evlogbook.ui.screens.settings.SettingsViewModel";

      static String com_asanga_evlogbook_ui_screens_charge_AddEditChargeViewModel = "com.asanga.evlogbook.ui.screens.charge.AddEditChargeViewModel";

      static String com_asanga_evlogbook_ui_screens_trip_TripListViewModel = "com.asanga.evlogbook.ui.screens.trip.TripListViewModel";

      static String com_asanga_evlogbook_ui_screens_reports_ReportsViewModel = "com.asanga.evlogbook.ui.screens.reports.ReportsViewModel";

      @KeepFieldType
      ChargeListViewModel com_asanga_evlogbook_ui_screens_charge_ChargeListViewModel2;

      @KeepFieldType
      HomeViewModel com_asanga_evlogbook_ui_screens_home_HomeViewModel2;

      @KeepFieldType
      AddEditTripViewModel com_asanga_evlogbook_ui_screens_trip_AddEditTripViewModel2;

      @KeepFieldType
      SettingsViewModel com_asanga_evlogbook_ui_screens_settings_SettingsViewModel2;

      @KeepFieldType
      AddEditChargeViewModel com_asanga_evlogbook_ui_screens_charge_AddEditChargeViewModel2;

      @KeepFieldType
      TripListViewModel com_asanga_evlogbook_ui_screens_trip_TripListViewModel2;

      @KeepFieldType
      ReportsViewModel com_asanga_evlogbook_ui_screens_reports_ReportsViewModel2;
    }
  }

  private static final class ViewModelCImpl extends EVLogbookApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AddEditChargeViewModel> addEditChargeViewModelProvider;

    private Provider<AddEditTripViewModel> addEditTripViewModelProvider;

    private Provider<ChargeListViewModel> chargeListViewModelProvider;

    private Provider<HomeViewModel> homeViewModelProvider;

    private Provider<ReportsViewModel> reportsViewModelProvider;

    private Provider<SettingsViewModel> settingsViewModelProvider;

    private Provider<TripListViewModel> tripListViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.addEditChargeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.addEditTripViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.chargeListViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.reportsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.tripListViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(7).put(LazyClassKeyProvider.com_asanga_evlogbook_ui_screens_charge_AddEditChargeViewModel, ((Provider) addEditChargeViewModelProvider)).put(LazyClassKeyProvider.com_asanga_evlogbook_ui_screens_trip_AddEditTripViewModel, ((Provider) addEditTripViewModelProvider)).put(LazyClassKeyProvider.com_asanga_evlogbook_ui_screens_charge_ChargeListViewModel, ((Provider) chargeListViewModelProvider)).put(LazyClassKeyProvider.com_asanga_evlogbook_ui_screens_home_HomeViewModel, ((Provider) homeViewModelProvider)).put(LazyClassKeyProvider.com_asanga_evlogbook_ui_screens_reports_ReportsViewModel, ((Provider) reportsViewModelProvider)).put(LazyClassKeyProvider.com_asanga_evlogbook_ui_screens_settings_SettingsViewModel, ((Provider) settingsViewModelProvider)).put(LazyClassKeyProvider.com_asanga_evlogbook_ui_screens_trip_TripListViewModel, ((Provider) tripListViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_asanga_evlogbook_ui_screens_charge_ChargeListViewModel = "com.asanga.evlogbook.ui.screens.charge.ChargeListViewModel";

      static String com_asanga_evlogbook_ui_screens_charge_AddEditChargeViewModel = "com.asanga.evlogbook.ui.screens.charge.AddEditChargeViewModel";

      static String com_asanga_evlogbook_ui_screens_trip_AddEditTripViewModel = "com.asanga.evlogbook.ui.screens.trip.AddEditTripViewModel";

      static String com_asanga_evlogbook_ui_screens_settings_SettingsViewModel = "com.asanga.evlogbook.ui.screens.settings.SettingsViewModel";

      static String com_asanga_evlogbook_ui_screens_trip_TripListViewModel = "com.asanga.evlogbook.ui.screens.trip.TripListViewModel";

      static String com_asanga_evlogbook_ui_screens_home_HomeViewModel = "com.asanga.evlogbook.ui.screens.home.HomeViewModel";

      static String com_asanga_evlogbook_ui_screens_reports_ReportsViewModel = "com.asanga.evlogbook.ui.screens.reports.ReportsViewModel";

      @KeepFieldType
      ChargeListViewModel com_asanga_evlogbook_ui_screens_charge_ChargeListViewModel2;

      @KeepFieldType
      AddEditChargeViewModel com_asanga_evlogbook_ui_screens_charge_AddEditChargeViewModel2;

      @KeepFieldType
      AddEditTripViewModel com_asanga_evlogbook_ui_screens_trip_AddEditTripViewModel2;

      @KeepFieldType
      SettingsViewModel com_asanga_evlogbook_ui_screens_settings_SettingsViewModel2;

      @KeepFieldType
      TripListViewModel com_asanga_evlogbook_ui_screens_trip_TripListViewModel2;

      @KeepFieldType
      HomeViewModel com_asanga_evlogbook_ui_screens_home_HomeViewModel2;

      @KeepFieldType
      ReportsViewModel com_asanga_evlogbook_ui_screens_reports_ReportsViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.asanga.evlogbook.ui.screens.charge.AddEditChargeViewModel 
          return (T) new AddEditChargeViewModel(singletonCImpl.chargeRepositoryProvider.get());

          case 1: // com.asanga.evlogbook.ui.screens.trip.AddEditTripViewModel 
          return (T) new AddEditTripViewModel(singletonCImpl.tripRepositoryProvider.get());

          case 2: // com.asanga.evlogbook.ui.screens.charge.ChargeListViewModel 
          return (T) new ChargeListViewModel(singletonCImpl.chargeRepositoryProvider.get());

          case 3: // com.asanga.evlogbook.ui.screens.home.HomeViewModel 
          return (T) new HomeViewModel(singletonCImpl.getDashboardDataUseCaseProvider.get());

          case 4: // com.asanga.evlogbook.ui.screens.reports.ReportsViewModel 
          return (T) new ReportsViewModel(singletonCImpl.tripRepositoryProvider.get(), singletonCImpl.chargeRepositoryProvider.get());

          case 5: // com.asanga.evlogbook.ui.screens.settings.SettingsViewModel 
          return (T) new SettingsViewModel(singletonCImpl.prefsRepositoryProvider.get());

          case 6: // com.asanga.evlogbook.ui.screens.trip.TripListViewModel 
          return (T) new TripListViewModel(singletonCImpl.tripRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends EVLogbookApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends EVLogbookApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends EVLogbookApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<DatabaseCallback> provideDatabaseCallbackProvider;

    private Provider<AppDatabase> provideAppDatabaseProvider;

    private Provider<ChargeDao> provideChargeDaoProvider;

    private Provider<ChargeRepository> chargeRepositoryProvider;

    private Provider<TripDao> provideTripDaoProvider;

    private Provider<TripRepository> tripRepositoryProvider;

    private Provider<PrefsDao> providePrefsDaoProvider;

    private Provider<PrefsRepository> prefsRepositoryProvider;

    private Provider<GetDashboardDataUseCase> getDashboardDataUseCaseProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideDatabaseCallbackProvider = DoubleCheck.provider(new SwitchingProvider<DatabaseCallback>(singletonCImpl, 3));
      this.provideAppDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 2));
      this.provideChargeDaoProvider = DoubleCheck.provider(new SwitchingProvider<ChargeDao>(singletonCImpl, 1));
      this.chargeRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ChargeRepository>(singletonCImpl, 0));
      this.provideTripDaoProvider = DoubleCheck.provider(new SwitchingProvider<TripDao>(singletonCImpl, 5));
      this.tripRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<TripRepository>(singletonCImpl, 4));
      this.providePrefsDaoProvider = DoubleCheck.provider(new SwitchingProvider<PrefsDao>(singletonCImpl, 8));
      this.prefsRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<PrefsRepository>(singletonCImpl, 7));
      this.getDashboardDataUseCaseProvider = DoubleCheck.provider(new SwitchingProvider<GetDashboardDataUseCase>(singletonCImpl, 6));
    }

    @Override
    public void injectEVLogbookApplication(EVLogbookApplication eVLogbookApplication) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.asanga.evlogbook.data.repository.ChargeRepository 
          return (T) new ChargeRepository(singletonCImpl.provideChargeDaoProvider.get());

          case 1: // com.asanga.evlogbook.data.dao.ChargeDao 
          return (T) DatabaseModule_ProvideChargeDaoFactory.provideChargeDao(singletonCImpl.provideAppDatabaseProvider.get());

          case 2: // com.asanga.evlogbook.data.db.AppDatabase 
          return (T) DatabaseModule_ProvideAppDatabaseFactory.provideAppDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideDatabaseCallbackProvider.get());

          case 3: // com.asanga.evlogbook.data.db.DatabaseCallback 
          return (T) DatabaseModule_ProvideDatabaseCallbackFactory.provideDatabaseCallback();

          case 4: // com.asanga.evlogbook.data.repository.TripRepository 
          return (T) new TripRepository(singletonCImpl.provideTripDaoProvider.get());

          case 5: // com.asanga.evlogbook.data.dao.TripDao 
          return (T) DatabaseModule_ProvideTripDaoFactory.provideTripDao(singletonCImpl.provideAppDatabaseProvider.get());

          case 6: // com.asanga.evlogbook.domain.usecase.GetDashboardDataUseCase 
          return (T) new GetDashboardDataUseCase(singletonCImpl.tripRepositoryProvider.get(), singletonCImpl.chargeRepositoryProvider.get(), singletonCImpl.prefsRepositoryProvider.get());

          case 7: // com.asanga.evlogbook.data.repository.PrefsRepository 
          return (T) new PrefsRepository(singletonCImpl.providePrefsDaoProvider.get());

          case 8: // com.asanga.evlogbook.data.dao.PrefsDao 
          return (T) DatabaseModule_ProvidePrefsDaoFactory.providePrefsDao(singletonCImpl.provideAppDatabaseProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
