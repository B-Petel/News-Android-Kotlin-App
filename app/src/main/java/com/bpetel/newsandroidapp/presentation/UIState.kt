package com.bpetel.newsandroidapp.presentation

import com.bpetel.newsandroidapp.data.model.ArticleDto

data class UIState(
    val uiList: List<ArticleDto>? = null
)
