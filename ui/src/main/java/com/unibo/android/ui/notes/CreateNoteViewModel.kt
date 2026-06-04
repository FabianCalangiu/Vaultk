package com.unibo.android.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unibo.android.domain.di.UseCasesProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CreateNoteUiState(
    val noteTitle: String = "",
    val noteText: String = ""
)

sealed interface CreateNoteEvent {
    data object NavigateToVault : CreateNoteEvent
}

class CreateNoteViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CreateNoteUiState())
    val uiState: StateFlow<CreateNoteUiState> = _uiState.asStateFlow()

    private val _events = Channel<CreateNoteEvent>()
    val events = _events.receiveAsFlow()

    fun onNoteTitleChange(value: String) {
        _uiState.update {
            it.copy(noteTitle = value)
        }
    }
    fun onNoteTextChange(value: String) {
        _uiState.update {
            it.copy(noteText = value)
        }
    }

    fun onSubmit() {
        val state = _uiState.value

        viewModelScope.launch {
            val result = UseCasesProvider.createNoteUseCase(state.noteTitle, state.noteText)

            if (result.isSuccess) {
                _events.send(CreateNoteEvent.NavigateToVault)
            } else {
                println(result.exceptionOrNull()?.message)
            }
        }
    }
}