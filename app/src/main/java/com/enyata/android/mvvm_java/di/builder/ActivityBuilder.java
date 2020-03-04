package com.enyata.android.mvvm_java.di.builder;

import com.enyata.android.mvvm_java.ui.login.LoginActivity;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivity;
import com.enyata.android.mvvm_java.ui.splash.SplashActivity;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class ActivityBuilder {
//    @ContributesAndroidInjector(modules = {
//            FeedActivityModule.class,
//            BlogFragmentProvider.class,
//            OpenSourceFragmentProvider.class})
//    abstract FeedActivity bindFeedActivity();
//
//    @ContributesAndroidInjector
//    abstract LoginActivity bindLoginActivity();
//
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();
//
    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();
}
