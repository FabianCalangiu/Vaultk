package com.unibo.android.ui.register

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

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)

sealed interface RegisterEvent {
    data object NavigateToVault : RegisterEvent
}

class RegisterViewModel : ViewModel() {

    private val registerUseCase = UseCasesProvider.registerUseCase
    private val sessionUseCase = UseCasesProvider.sessionUseCase

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val _events = Channel<RegisterEvent>()
    val events = _events.receiveAsFlow()

    fun onEmailChange(value: String) {
        _uiState.update {
            it.copy(email = value)
        }
    }
    fun onPasswordChange(value: String) {
        _uiState.update {
            it.copy(password = value)
        }
    }
    fun onConfirmPasswordChange(value: String) {
        _uiState.update {
            it.copy(confirmPassword = value)
        }
    }

    fun onSubmit() {
        val state = _uiState.value

        viewModelScope.launch {
            val registerResult = registerUseCase(state.email, state.password)

            if (registerResult.isSuccess) {
                val sessionResult = sessionUseCase(state.email, "register")

                if (sessionResult.isSuccess) {
                    _events.send(RegisterEvent.NavigateToVault)
                } else {
                    println(sessionResult.exceptionOrNull()?.message)
                }
            } else {
                println(registerResult.exceptionOrNull()?.message)
            }
        }
    }
}