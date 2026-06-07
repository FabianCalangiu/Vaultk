package com.unibo.android.data.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object WorkScheduler {
    fun scheduleSecurityCheck(
        context: Context
    ) {

        val request =
            PeriodicWorkRequestBuilder<
                    SecurityCheckWorker
                    >(
                24,
                TimeUnit.HOURS
            )
                .build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "security_check",
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
    }
}