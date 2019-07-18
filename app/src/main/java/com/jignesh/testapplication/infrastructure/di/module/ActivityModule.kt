package com.jignesh.testapplication.infrastructure.di.module

import com.jignesh.testapplication.infrastructure.di.scope.ViewScope
import com.jignesh.testapplication.presentation.home.HomeActivity
import com.jignesh.testapplication.presentation.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ViewScope
    @ContributesAndroidInjector
    abstract fun splashActivityInjector(): SplashActivity

    @ViewScope
    @ContributesAndroidInjector
    abstract fun HomeActivityInjector(): HomeActivity

}