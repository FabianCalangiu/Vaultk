package com.unibo.android.domain.di

import com.unibo.android.domain.usecases.FetchAccommodationListUpdatesUseCase
import com.unibo.android.domain.usecases.FetchAccommodationListUpdatesUseCaseImpl
import com.unibo.android.domain.usecases.SetOnboardingShowedUseCase
import com.unibo.android.domain.usecases.SetOnboardingShowedUseCaseImpl
import com.unibo.android.domain.usecases.ShowOnboardingUseCase
import com.unibo.android.domain.usecases.ShowOnboardingUseCaseImpl
import com.unibo.android.domain.usecases.StartFetchAccommodationListUseCase
import com.unibo.android.domain.usecases.StartFetchAccommodationListUseCaseImpl

object UseCasesProvider {

    lateinit var startFetchAccommodationListUseCase: StartFetchAccommodationListUseCase
    lateinit var fetchAccommodationListUpdatesUseCase: FetchAccommodationListUpdatesUseCase
    //TODO DATASTORE 5 definire implementazione UseCase in UseCasesProvider
    lateinit var setOnboardingShowedUseCase: SetOnboardingShowedUseCase
    lateinit var showOnboardingUseCase: ShowOnboardingUseCase


    fun setup(
        repositoryProvider: RepositoryProvider,
    ) {
        startFetchAccommodationListUseCase = StartFetchAccommodationListUseCaseImpl(
            accommodationRepository = repositoryProvider.accommodationRepository
        )

        fetchAccommodationListUpdatesUseCase = FetchAccommodationListUpdatesUseCaseImpl(
            accommodationRepository = repositoryProvider.accommodationRepository
        )

        //TODO DATASTORE 5 definire implementazione UseCase in UseCasesProvider
        setOnboardingShowedUseCase = SetOnboardingShowedUseCaseImpl(
            preferenceRepository = repositoryProvider.preferenceRepository
        )

        showOnboardingUseCase = ShowOnboardingUseCaseImpl(
            preferenceRepository = repositoryProvider.preferenceRepository
        )
    }
}