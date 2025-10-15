package com.bpetel.newsandroidapp.presentation

import com.bpetel.newsandroidapp.domain.Article

data class UIState(
    val uiList: List<Article>? = null
)