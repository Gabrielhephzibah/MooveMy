package com.enyata.android.mvvm_java;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.ui.login.LoginViewModel;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivityViewModel;
import com.enyata.android.mvvm_java.ui.splash.SplashViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import javax.inject.Inject;

public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            //noinspection unchecked
            return (T) new MainActivityViewModel(dataManager, schedulerProvider);
        }
         else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashViewModel(dataManager,schedulerProvider);
            }
        else if (modelClass.isAssignableFrom(LoginViewModel.class)){
        //noinspection unchecked
        return (T) new LoginViewModel(dataManager, schedulerProvider);
    }

//        } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
//            //noinspection unchecked
//            return (T) new MainViewModel(dataManager,schedulerProvider);
//        }
//        else if (modelClass.isAssignableFrom(BlogViewModel.class)) {
//            //noinspection unchecked
//            return (T) new BlogViewModel(dataManager,schedulerProvider);
//        }
//        else if (modelClass.isAssignableFrom(RateUsViewModel.class)) {
//            //noinspection unchecked
//            return (T) new RateUsViewModel(dataManager,schedulerProvider);
//        }
//        else if (modelClass.isAssignableFrom(OpenSourceViewModel.class)) {
//            //noinspection unchecked
//            return (T) new OpenSourceViewModel(dataManager,schedulerProvider);
//        }
//        else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
//            //noinspection unchecked
//            return (T) new SplashViewModel(dataManager,schedulerProvider);
//        }
            throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());

    }
}
