package com.bpetel.newsandroidapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object MainScreen: Screen()
}