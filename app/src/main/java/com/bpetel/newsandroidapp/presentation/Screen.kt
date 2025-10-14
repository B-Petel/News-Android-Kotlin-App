package com.bpetel.newsandroidapp.presentation

import com.bpetel.newsandroidapp.domain.Article
import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object MainScreen: Screen

    @Serializable
    data class DetailScreen(val id: String): Screen
}