package com.unibo.android.vaultk

import android.app.Application
import com.unibo.android.data.di.RepositoryProviderImpl
import com.unibo.android.data.worker.WorkScheduler
import com.unibo.android.domain.di.UseCasesProvider

class CustomApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        UseCasesProvider.setup(
            repositoryProvider = RepositoryProviderImpl(this)
        )

        WorkScheduler.scheduleSecurityCheck(this)
    }
}