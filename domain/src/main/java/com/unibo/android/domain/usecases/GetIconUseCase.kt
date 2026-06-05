package com.unibo.android.domain.usecases

import com.unibo.android.domain.repositories.IconRepository

interface GetIconUseCase {
    suspend operator fun invoke(domain: String): String
}

class GetIconUseCaseImpl(private val iconRepository: IconRepository) : GetIconUseCase {
    override suspend operator fun invoke(domain: String): String {
        return iconRepository.getIconUrl(domain)
    }
}