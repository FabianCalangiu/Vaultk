package com.unibo.android.domain.usecases

import com.unibo.android.domain.repositories.PreferenceRepository

//TODO DATASTORE 4 UseCase per salvare se ho visualizzato onBoarding
interface SetOnboardingShowedUseCase {
    suspend fun invoke()
}

class SetOnboardingShowedUseCaseImpl(
    private val preferenceRepository: PreferenceRepository,
) : SetOnboardingShowedUseCase {
    override suspend fun invoke() =
        preferenceRepository.setOnboardingShowed()

}