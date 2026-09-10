package com.example.submission.ui.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submission.data.model.Show
import com.example.submission.data.repository.TvShowRepository
import com.example.submission.di.Injection
import com.example.submission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: TvShowRepository = Injection.tvShowRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Show>> = MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<Show>> get() = _uiState

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDetailShow(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val result = repository.getDetailShow(id)
                _uiState.value = UiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}