package com.bpetel.newsandroidapp.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.bpetel.newsandroidapp.data.model.ArticleDto
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel()
) {

    val state = mainViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getFrenchArticles()
    }

    LazyColumn {
        println("we got: ${state.value.uiList?.size}")
    }
}

@Composable
fun ArticleComponent(
    articleList : List<ArticleDto>
) {

}