package com.example.twossreensofkinopoisk.ui.detailed_information

import androidx.lifecycle.ViewModel
import com.example.twossreensofkinopoisk.ui.list_of_movies.ListOfMoviesUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DetailedInformationUIState(
    val isShowLoading: Boolean = false
)

class DetailedInformationVM: ViewModel() {

    private var _uiState: MutableStateFlow<DetailedInformationUIState> = MutableStateFlow(DetailedInformationUIState())
    val uiState: StateFlow<DetailedInformationUIState> = _uiState.asStateFlow()
}