package com.unibo.android.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unibo.android.domain.usecases.SetOnboardingShowedUseCase
import com.unibo.android.domain.usecases.ShowOnboardingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

//TODO DATASTORE 5 Creare ViewModel associato alla Splash Activity
class SplashViewModel(
    private val showOnboardingUseCase: ShowOnboardingUseCase,
    private val setOnboardingShowedUseCase: SetOnboardingShowedUseCase,
): ViewModel() {

    private val _showOnBoarding = MutableStateFlow<Boolean?>(null)
    val showOnBoarding: Flow<Boolean?> = _showOnBoarding


    fun fetchShowOnBoarding() {
        viewModelScope.launch {
            showOnboardingUseCase().collect { show ->
                _showOnBoarding.emit(show)
            }
        }
    }

    fun setOnBoardingShowed(){
        viewModelScope.launch {
            setOnboardingShowedUseCase.invoke()
        }
    }

}