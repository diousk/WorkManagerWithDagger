package com.example.david_chen.workmanagerplusdagger

import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.david_chen.workmanagerplusdagger.di.DaggerAppComponent
import com.example.david_chen.workmanagerplusdagger.worker.DaggerAwareWorkerFactory
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class App: DaggerApplication() {
    companion object {
        lateinit var instance: App
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        configureWorkManager()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    @Inject lateinit var daggerAwareWorkerFactory: DaggerAwareWorkerFactory

    private fun configureWorkManager() {
        val config = Configuration.Builder()
            .setWorkerFactory(daggerAwareWorkerFactory)
            .build()

        WorkManager.initialize(this, config)
    }
}