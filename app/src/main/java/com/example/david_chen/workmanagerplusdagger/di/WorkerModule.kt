package com.example.david_chen.workmanagerplusdagger.di

import androidx.work.ListenableWorker
import com.example.david_chen.workmanagerplusdagger.worker.ChildWorkerFactory
import com.example.david_chen.workmanagerplusdagger.worker.HelloWorker
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
interface WorkerBindingModule {
    @Binds
    @IntoMap
    @WorkerKey(HelloWorker::class)
    fun bindHelloWorldWorker(factory: HelloWorker.Factory): ChildWorkerFactory<out ListenableWorker>
}

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val value: KClass<out ListenableWorker>)