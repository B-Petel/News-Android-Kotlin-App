package com.bpetel.newsandroidapp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bpetel.newsandroidapp.domain.Article
import com.bpetel.newsandroidapp.presentation.component.ArticleComponent
import com.bpetel.newsandroidapp.presentation.viewmodel.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel(),
    modifier: Modifier,
    onItemClick: (Article) -> Unit
) {
    val state = mainViewModel.uiState.collectAsState()

    if (state.value.uiList != null) {
        LazyColumn(
            modifier = modifier.background(color = Color.LightGray)
        ) {
            items(state.value.uiList!!) { it ->
                ArticleComponent(
                    it,
                    it.title,
                    it.contentExcerpt,
                    it.author,
                    it.imageUrl
                ) { onItemClick(it) }
            }
            println("we got: ${state.value.uiList?.size}")
        }
    }
}

