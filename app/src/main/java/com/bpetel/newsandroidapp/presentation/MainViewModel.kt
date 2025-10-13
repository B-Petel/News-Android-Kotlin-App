package com.bpetel.newsandroidapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpetel.newsandroidapp.domain.LumenFeedRepository
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

    fun getArticles() {
        viewModelScope.launch {
            repository.getArticles().map {
                articleListDto -> UIState(articleListDto.data)
            }.collect { it ->
                _uiState.update { it }
            }
        }
    }

    fun getFrenchArticles() {
        viewModelScope.launch {
            repository.getArticleFilterByLanguage("en")
                .map {
                    articles -> UIState(articles.data)
                }
                .collect { response ->
                    _uiState.update { response }
                }
            }
        }
}