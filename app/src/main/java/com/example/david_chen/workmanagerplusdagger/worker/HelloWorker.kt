package com.example.david_chen.workmanagerplusdagger.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.chenweiming.mykktapplication.api.WikiApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Provider

class HelloWorker(
    params: WorkerParameters,
    private val context: Context,
    private val wikiApiService: WikiApiService // our dependency
): Worker(context, params) {
    override fun doWork(): Result {
        Log.d("HW", "doWork on ${Thread.currentThread().name}")

        val result = wikiApiService.getPage(6191053)
            .blockingGet()
        Log.d("HW", "6191053: ${result}")
        Log.d("HW", "key2: ${inputData.getInt("key2", 0)}")

        return Result.success()
    }

    class Factory @Inject constructor(
        private val context: Provider<Context>, // provide from AppModule
        private val wikiApiService: Provider<WikiApiService> // provide from NetworkModule
    ) : ChildWorkerFactory<HelloWorker> {
        override fun create(params: WorkerParameters): HelloWorker {
            return HelloWorker(params, context.get(), wikiApiService.get())
        }
    }
}