package com.example.david_chen.workmanagerplusdagger.di

import com.example.david_chen.workmanagerplusdagger.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilder::class,
    NetworkModule::class,
    WorkerBindingModule::class,
    AppModule::class
])
interface AppComponent: AndroidInjector<App> {
    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<App>()
}