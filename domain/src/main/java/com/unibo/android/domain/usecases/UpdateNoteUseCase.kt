package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.NoteEntryModel
import com.unibo.android.domain.repositories.NoteRepository

interface UpdateNoteUseCase {
    suspend operator fun invoke(
        entryNote: NoteEntryModel
    ): Result<Unit>
}

class UpdateNoteUseCaseImpl (
    private val noteRepository: NoteRepository
) : UpdateNoteUseCase {
    override suspend operator fun invoke(entryNote: NoteEntryModel): Result<Unit> {
        return try {
            noteRepository.updateNote(entryNote)
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}