package com.unibo.android.ui.accounts

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

data class CreateAccUiState(
    val accountTitle: String = "",
    val emailAccount: String = "",
    val passwordAccount: String = ""
)

sealed interface CreateAccEvent {
    data object NavigateToVault : CreateAccEvent
}

class CreateAccViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CreateAccUiState())
    val uiState: StateFlow<CreateAccUiState> = _uiState.asStateFlow()

    private val _events = Channel<CreateAccEvent>()
    val events = _events.receiveAsFlow()

    fun onAccountTitleChange(value: String) {
        _uiState.update {
            it.copy(accountTitle = value)
        }
    }
    fun onEmailAccountChange(value: String) {
        _uiState.update {
            it.copy(emailAccount = value)
        }
    }
    fun onPasswordAccountChange(value: String) {
        _uiState.update {
            it.copy(passwordAccount = value)
        }
    }

    fun onSubmit() {
        val state = _uiState.value

        viewModelScope.launch {
            val result = UseCasesProvider.createAccountUseCase(
                state.accountTitle,
                state.emailAccount,
                state.passwordAccount
            )

            if (result.isSuccess) {
                _events.send(CreateAccEvent.NavigateToVault)
            } else {
                println(result.exceptionOrNull()?.message)
            }
        }
    }
}