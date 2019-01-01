package com.example.david_chen.workmanagerplusdagger

import android.os.Bundle
import android.util.Log
import androidx.work.*
import com.example.chenweiming.mykktapplication.api.WikiApiService
import com.example.david_chen.workmanagerplusdagger.worker.HelloWorker
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject lateinit var wikiApiService: WikiApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        OneTimeWorker.setOnClickListener {
            scheduleOneTimeWork()
        }

        PeriodWorker.setOnClickListener {
            schedulePeriodWork()
        }

        ApiTest.setOnClickListener {
            testApi()
        }
    }

    private fun scheduleOneTimeWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // Add WorkRequest to save the image to the filesystem
        val save = OneTimeWorkRequest.Builder(HelloWorker::class.java)
            .setConstraints(constraints)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setInputData(createInputDataForUri())
            .addTag("HELLO")
            .build()

        WorkManager.getInstance()
            .enqueueUniqueWork("WW", ExistingWorkPolicy.KEEP, save)
    }

    private fun schedulePeriodWork() {

    }

    private fun testApi() {
        wikiApiService.getPage(6191053)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ result ->
                Log.d("MAIN", "6191053: ${result}")
            }, {
                Log.d("MAIN", "error: $it")
            })
    }

    private var count = 0
    private fun createInputDataForUri(): Data {
        count++
        val builder = Data.Builder()
        builder.putString("key1", "100")
        builder.putInt("key2", 20000 + count)
        return builder.build()
    }
}
