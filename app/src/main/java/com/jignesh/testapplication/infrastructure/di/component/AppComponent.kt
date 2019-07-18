package com.jignesh.testapplication.infrastructure.di.component

import android.content.Context
import com.jignesh.testapplication.domain.repository.LocationRespository
import com.jignesh.testapplication.domain.repository.WeatherRepository
import com.jignesh.testapplication.infrastructure.TestApplication
import com.jignesh.testapplication.infrastructure.di.module.ActivityModule
import com.jignesh.testapplication.infrastructure.di.module.ApplicationModule
import com.jignesh.testapplication.infrastructure.di.module.RepositoryModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    ApplicationModule::class,
    RepositoryModule::class
])
interface AppComponent : AndroidInjector<TestApplication> {
    fun provideWeatherRepository():WeatherRepository
    fun provideLocationRepository():LocationRespository
}