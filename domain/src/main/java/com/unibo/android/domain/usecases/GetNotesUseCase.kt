package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.NoteEntryModel
import com.unibo.android.domain.repositories.NoteRepository
import com.unibo.android.domain.repositories.SessionRepository
import kotlinx.coroutines.flow.first

interface GetNotesUseCase {
    suspend operator fun invoke(): List<NoteEntryModel>
}

class GetNotesUseCaseImpl(
    private val noteRepository: NoteRepository,
    private val sessionRepository: SessionRepository
) : GetNotesUseCase {
    override suspend fun invoke(): List<NoteEntryModel> {
        val userId = sessionRepository.getUserId().first()?: return emptyList()

        return noteRepository.getAllNotes(userId)
    }
}