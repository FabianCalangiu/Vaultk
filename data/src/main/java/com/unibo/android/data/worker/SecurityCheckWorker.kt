package com.unibo.android.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.unibo.android.domain.di.UseCasesProvider

class SecurityCheckWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {

            val accounts =
                UseCasesProvider.getAccountsUseCase()

            accounts.forEach { account ->

                UseCasesProvider
                    .checkPasswordBreachUseCase(
                        account.password
                    )
            }

            Result.success()

        } catch (exception: Exception) {

            Result.failure()
        }
    }
}