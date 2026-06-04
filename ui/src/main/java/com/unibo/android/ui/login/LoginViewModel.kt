package com.unibo.android.uicompose.login

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

data class LoginUiState(
    val email: String = "",
    val password: String = ""
)

sealed interface LoginEvent {
    data object NavigateToVault : LoginEvent
}

class LoginViewModel : ViewModel() {

    private val loginUseCase = UseCasesProvider.loginUseCase
    private val sessionUseCase = UseCasesProvider.sessionUseCase

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _events = Channel<LoginEvent>()
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

    fun onSubmit() {
        val state = _uiState.value

        viewModelScope.launch {
            val resultLogin = loginUseCase(state.email, state.password)
            val resultSession = sessionUseCase(state.email, "login")

            if (resultLogin.isFailure || resultSession.isFailure) {
                println("Something went wrong")
            } else {
                _events.send(LoginEvent.NavigateToVault)
            }
        }
    }
}