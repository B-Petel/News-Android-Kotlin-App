package com.bpetel.newsandroidapp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bpetel.newsandroidapp.domain.ArticleDto
import com.bpetel.newsandroidapp.presentation.UIState
import com.bpetel.newsandroidapp.presentation.component.ArticleComponent
import com.bpetel.newsandroidapp.presentation.viewmodel.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel(),
    modifier: Modifier,
    onItemClick: (ArticleDto) -> Unit
) {
    mainViewModel.uiState.collectAsState().value.let { state ->
        when(state) {
            is UIState.Error ->
                println("UIState.Error : " + state.error)
            UIState.Loading ->
                println("UIState.Loading")
            is UIState.Success ->
                LazyColumn(
                    modifier = modifier.background(color = Color.LightGray)
                ) {
                    println("UIState.Success")
                    items(state.articles) { it ->
                        ArticleComponent(
                            it.title,
                            it.contentExcerpt,
                            it.imageUrl
                        ) { onItemClick(it) }
                    }
                }
        }
    }
}

