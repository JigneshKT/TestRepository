package com.jignesh.testapplication.infrastructure

import com.jignesh.testapplication.infrastructure.di.component.DaggerAppComponent
import com.jignesh.testapplication.infrastructure.di.module.ApplicationModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class TestApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

}