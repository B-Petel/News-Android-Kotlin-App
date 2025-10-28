package com.bpetel.newsandroidapp.presentation

sealed interface UiEvent {
    data object Refresh: UiEvent
}
