package com.unibo.android.ui.vault

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unibo.android.domain.di.UseCasesProvider
import com.unibo.android.domain.models.AccountEntryModel
import com.unibo.android.domain.models.NoteEntryModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class VaultUiState(
    val accounts: List<AccountEntryModel> = emptyList(),
    val notes: List<NoteEntryModel> = emptyList(),
    val selectedNote: NoteEntryModel? = null,
    val selectedAccount: AccountEntryModel? = null
)

class VaultViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VaultUiState())
    val uiState: StateFlow<VaultUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    accounts = UseCasesProvider.getAccountsUseCase(),
                    notes = UseCasesProvider.getNotesUseCase()
                )
            }
        }
    }

    fun onSelectNote(note: NoteEntryModel?) {
        _uiState.update {
            it.copy(selectedNote = note)
        }
    }
    fun onSelectAccount(account: AccountEntryModel?) {
        _uiState.update {
            it.copy(selectedAccount = account)
        }
    }

    fun onDeleteNote(note: NoteEntryModel) {
        viewModelScope.launch {
            val result = UseCasesProvider.deleteNoteUseCase(note)
            if (result.isSuccess) {
                _uiState.update {
                    it.copy(
                        notes = UseCasesProvider.getNotesUseCase(),
                        selectedNote = null
                    )
                }
            } else {
                println(result.exceptionOrNull()?.message)
            }
        }
    }

    fun onUpdateNote(entry: NoteEntryModel) {
        viewModelScope.launch {
            val result = UseCasesProvider.updateNoteUseCase(entry)
            if (result.isSuccess) {
                _uiState.update {
                    it.copy(
                        notes = UseCasesProvider.getNotesUseCase(),
                        selectedNote = entry
                    )
                }
            } else {
                println(result.exceptionOrNull()?.message)
            }
        }
    }

    fun onDeleteAccount(account: AccountEntryModel) {
        viewModelScope.launch {
            val result = UseCasesProvider.deleteAccountUseCase(account)
            if (result.isSuccess) {
                _uiState.update {
                    it.copy(
                        accounts = UseCasesProvider.getAccountsUseCase(),
                        selectedAccount = null
                    )
                }
            } else {
                println(result.exceptionOrNull()?.message)
            }
        }
    }

    fun onUpdateAccount(updatedEntry: AccountEntryModel) {
        viewModelScope.launch {
            val result = UseCasesProvider.updateAccountUseCase(updatedEntry)
            if (result.isSuccess) {
                _uiState.update {
                    it.copy(
                        accounts = UseCasesProvider.getAccountsUseCase(),
                        selectedAccount = updatedEntry
                    )
                }
            } else {
                println(result.exceptionOrNull()?.message)
            }
        }
    }
}