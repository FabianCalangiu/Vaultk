package com.unibo.android.uicompose.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unibo.android.domain.usecases.FetchAccommodationListUpdatesUseCase
import com.unibo.android.domain.usecases.StartFetchAccommodationListUseCase

class HomeViewModelFactory(
    private val startFetchAccommodationListUseCase: StartFetchAccommodationListUseCase,
    private val fetchAccommodationListUpdatesUseCase: FetchAccommodationListUpdatesUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomepageViewModel::class.java)) {
            return HomepageViewModel(
                startFetchAccommodationListUseCase,
                fetchAccommodationListUpdatesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}