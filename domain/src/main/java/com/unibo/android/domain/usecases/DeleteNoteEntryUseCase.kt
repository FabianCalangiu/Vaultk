package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.NoteEntryModel
import com.unibo.android.domain.repositories.NoteRepository
import com.unibo.android.domain.repositories.SessionRepository
import kotlinx.coroutines.flow.first

interface DeleteNoteUseCase {
    suspend operator fun invoke(
        title: String,
        content: String,
        noteEntriesList: List<NoteEntryModel>
    ): Result<Unit>
}

class DeleteNoteUseCaseImpl (
    private val noteRepository: NoteRepository,
    private val sessionRepository: SessionRepository
) : DeleteNoteUseCase {
    override suspend operator fun invoke(
        title: String,
        content: String,
        noteEntriesList: List<NoteEntryModel>
    ): Result<Unit> {
        val NULL: Long = 0

        return try {
            val userId = sessionRepository.getUserId().first()
            if(userId == NULL) {
                return Result.failure(Exception("note id does not exist"))
            }

            val entry = NoteEntryModel(
                id = 0,
                title = title,
                content = content,
                userId = userId
            )

            noteRepository.deleteUser(entry)

            return Result.success(Unit)

        } catch (exception: Exception) {

            return Result.failure(exception)
        }
    }
}