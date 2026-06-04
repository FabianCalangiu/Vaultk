package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.NoteEntryModel
import com.unibo.android.domain.repositories.NoteRepository
import com.unibo.android.domain.repositories.SessionRepository
import com.unibo.android.domain.security.CryptoManager
import kotlinx.coroutines.flow.first

interface GetNotesUseCase {
    suspend operator fun invoke(): List<NoteEntryModel>
}

class GetNotesUseCaseImpl(
    private val noteRepository: NoteRepository,
    private val sessionRepository: SessionRepository,
    private val cryptoManager: CryptoManager
) : GetNotesUseCase {
    override suspend fun invoke(): List<NoteEntryModel> {
        val userId = sessionRepository.getUserId().first()?: return emptyList()

        return noteRepository.getAllNotes(userId).map {note ->
            note.copy(
                content = cryptoManager.decrypt(note.content)
            )
        }
    }
}