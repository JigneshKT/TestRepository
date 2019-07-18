package com.jignesh.testapplication.infrastructure.di.module

import com.jignesh.testapplication.data.repository.implementation.api.LocationRepositoryImp
import com.jignesh.testapplication.data.repository.implementation.api.WeatherRepositoryImp
import com.jignesh.testapplication.domain.repository.LocationRespository
import com.jignesh.testapplication.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun weatherRepository(weatherRespositoryImp: WeatherRepositoryImp): WeatherRepository

    @Binds
    abstract fun locationRepository(locationRespository: LocationRepositoryImp): LocationRespository
}