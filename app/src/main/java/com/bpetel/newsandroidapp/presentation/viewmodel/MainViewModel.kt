package com.bpetel.newsandroidapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpetel.newsandroidapp.domain.LumenFeedRepository
import com.bpetel.newsandroidapp.presentation.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel (
    private val repository: LumenFeedRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch {
            repository.getArticles()
                .map { articleList ->
                    UIState(articleList)
                }
                .collect { response ->
                    _uiState.update { response }
                }
        }
    }
}