package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.NoteEntryModel
import com.unibo.android.domain.repositories.NoteRepository
import com.unibo.android.domain.repositories.SessionRepository
import kotlinx.coroutines.flow.first

interface AddNoteEntryUseCase {

    suspend operator fun invoke(
        title: String,
        content: String
    ): Result<Unit>
}

class AddNoteEntryUseCaseImpl(
    private val noteRepository: NoteRepository,
    private val sessionRepository: SessionRepository
) : AddNoteEntryUseCase {

    override suspend operator fun invoke(
        title: String,
        content: String
    ): Result<Unit> {
        val NULL: Long = 0

        return try {

            if(title.isBlank() || content.isBlank()) {
                return Result.failure(
                    Exception("Cannot contains empty values")
                )
            }

            val userId = sessionRepository.getUserId().first()

            if(userId == NULL) {
                return Result.failure(
                    Exception("note id does not exist")
                )
            }

            noteRepository.createNote(NoteEntryModel(title = title, content = content, userId = userId))

            Result.success(Unit)

        } catch (exception: Exception) {

            Result.failure(exception)
        }
    }
}