package com.unibo.android.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unibo.android.domain.models.AccommodationType
import com.unibo.android.domain.usecases.FetchAccommodationListUpdatesUseCase
import com.unibo.android.domain.usecases.StartFetchAccommodationListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val startFetchAccommodationListUseCase: StartFetchAccommodationListUseCase,
    private val fetchAccommodationListUpdatesUseCase: FetchAccommodationListUpdatesUseCase
): ViewModel() {

    private val _accommodationTypeList = MutableStateFlow<List<AccommodationType>>(emptyList())
    val accommodationTypeList: StateFlow<List<AccommodationType>> = _accommodationTypeList

    private val _showLoader = MutableStateFlow<Boolean>(false)
    val showLoader: StateFlow<Boolean> = _showLoader

    init {
        fetchAccommodationListUpdates()
    }

    fun startFetchAccommodationList() {
        _showLoader.tryEmit(true)
        startFetchAccommodationListUseCase.invoke()
    }

    private fun fetchAccommodationListUpdates() {
        viewModelScope.launch {
            fetchAccommodationListUpdatesUseCase.invoke().collect { accommodationList ->
                _showLoader.tryEmit(false)
                _accommodationTypeList.emit(accommodationList)
            }
        }
    }
}