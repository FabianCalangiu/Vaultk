package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.NoteEntryModel
import com.unibo.android.domain.repositories.NoteRepository
import com.unibo.android.domain.security.CryptoManager

interface UpdateNoteUseCase {
    suspend operator fun invoke(
        entryNote: NoteEntryModel
    ): Result<Unit>
}

class UpdateNoteUseCaseImpl (
    private val noteRepository: NoteRepository,
    private val cryptoManager: CryptoManager
) : UpdateNoteUseCase {
    override suspend operator fun invoke(entryNote: NoteEntryModel): Result<Unit> {
        val encryptedNote = entryNote.copy(content = cryptoManager.encrypt(entryNote.content))

        return try {
            noteRepository.updateNote(encryptedNote)
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}