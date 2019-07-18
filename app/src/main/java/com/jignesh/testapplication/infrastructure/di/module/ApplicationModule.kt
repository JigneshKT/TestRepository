package com.jignesh.testapplication.infrastructure.di.module

import android.content.Context
import com.jignesh.testapplication.infrastructure.TestApplication
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: TestApplication){
    @Provides
    @Singleton
    fun provideContext(): Context = app
}