package com.bpetel.newsandroidapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpetel.newsandroidapp.data.remote.NetworkResult
import com.bpetel.newsandroidapp.domain.LumenFeedRepository
import com.bpetel.newsandroidapp.presentation.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel (
    private val repository: LumenFeedRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch {
            repository.getArticles().collect { response ->
                when(response) {
                    is NetworkResult.Error ->
                        _uiState.update { UIState.Error(response.message) }
                    is NetworkResult.Success -> {
                        _uiState.update { UIState.Success(response.data) }
                    }
                }
            }
        }
    }
}