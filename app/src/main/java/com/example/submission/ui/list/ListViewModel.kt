package com.example.submission.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submission.data.model.Show

import com.example.submission.data.remote.response.TvShows
import com.example.submission.data.repository.TvShowRepository
import com.example.submission.di.Injection
import com.example.submission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: TvShowRepository = Injection.tvShowRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Show>>> = MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<List<Show>>> get() = _uiState

    init {
        getShows()
    }

    fun getShows() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val result = repository.getShows()
                _uiState.value = UiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown Error")
            }
            Log.d("Show", _uiState.value.toString())
        }
    }
}