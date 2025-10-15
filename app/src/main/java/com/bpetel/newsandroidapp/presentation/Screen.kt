package com.bpetel.newsandroidapp.presentation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object MainScreen: Screen()
}