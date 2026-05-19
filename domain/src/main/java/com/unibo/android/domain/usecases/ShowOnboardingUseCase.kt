package com.unibo.android.domain.usecases

import com.unibo.android.domain.repositories.PreferenceRepository
import kotlinx.coroutines.flow.Flow

//TODO DATASTORE 3 UseCase per leggere se visualizzare onBoarding

interface ShowOnboardingUseCase : () -> Flow<Boolean>

class ShowOnboardingUseCaseImpl(
    private val preferenceRepository: PreferenceRepository,
) : ShowOnboardingUseCase {
    override fun invoke() =
        preferenceRepository.needShowOnBoarding

}