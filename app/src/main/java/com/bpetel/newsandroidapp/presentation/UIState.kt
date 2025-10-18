package com.bpetel.newsandroidapp.presentation

import com.bpetel.newsandroidapp.domain.ArticleDto

sealed class UIState {
    data object Loading: UIState()
    data class Success(val articles: List<ArticleDto>): UIState()
    data class Error(val error: String?): UIState()
}