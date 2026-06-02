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
            val userId = sessionRepository.getUserId().first()
            if(userId == NULL) {
                return Result.failure(Exception("note id does not exist"))
            }

            if(title.isBlank() || content.isBlank()) {
                return Result.failure(Exception("Cannot contains empty values"))
            }

            noteRepository.createNote(
                NoteEntryModel(
                    id = 0,
                    title = title,
                    content = content,
                    userId = userId
                )
            )

            return Result.success(Unit)

        } catch (exception: Exception) {

            return Result.failure(exception)
        }
    }
}