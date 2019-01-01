package com.example.david_chen.workmanagerplusdagger.worker

import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

interface ChildWorkerFactory<T : ListenableWorker> {
    fun create(params: WorkerParameters): T
}