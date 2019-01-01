package com.example.david_chen.workmanagerplusdagger.di

import android.content.Context
import com.example.david_chen.workmanagerplusdagger.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideContext(application: App): Context = application.applicationContext
}