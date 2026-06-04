package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.NoteEntryModel
import com.unibo.android.domain.repositories.NoteRepository

interface DeleteNoteUseCase {
    suspend operator fun invoke(
        entry: NoteEntryModel
    ): Result<Unit>
}

class DeleteNoteUseCaseImpl (
    private val noteRepository: NoteRepository,
) : DeleteNoteUseCase {
    override suspend operator fun invoke(
        entry: NoteEntryModel
    ): Result<Unit> {
        return try {
            noteRepository.deleteNote(entry)
            return Result.success(Unit)
        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }
}