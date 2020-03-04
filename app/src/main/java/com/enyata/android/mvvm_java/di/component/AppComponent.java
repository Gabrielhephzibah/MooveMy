package com.enyata.android.mvvm_java.di.component;


import android.app.Application;

import com.enyata.android.mvvm_java.MvvmApp;
import com.enyata.android.mvvm_java.di.builder.ActivityBuilder;
import com.enyata.android.mvvm_java.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {
    void inject(MvvmApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
