package com.bpetel.newsandroidapp.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bpetel.newsandroidapp.domain.ArticleDto
import com.bpetel.newsandroidapp.presentation.UIState
import com.bpetel.newsandroidapp.presentation.UiEvent
import com.bpetel.newsandroidapp.presentation.component.PullToRefreshComponent
import com.bpetel.newsandroidapp.presentation.viewmodel.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier,
    mainViewModel: MainViewModel = koinViewModel(),
    onItemClick: (ArticleDto) -> Unit
) {
    var isRefreshing by remember { mutableStateOf(false) }

    mainViewModel.uiState.collectAsState().value.let { state ->
        when(state) {
            is UIState.Error ->
                println("UIState.Error : " + state.error)
            UIState.Loading ->
                println("UIState.Loading")
            is UIState.Success ->
                PullToRefreshComponent(
                    state.articles,
                    isRefreshing,
                    {
                        isRefreshing = true
                        mainViewModel.onEvent(UiEvent.Refresh)
                        isRefreshing = false
                    },
                    { onItemClick(it) },
                    modifier
                )
        }
    }
}

