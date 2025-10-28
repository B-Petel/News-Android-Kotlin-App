package com.bpetel.newsandroidapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bpetel.newsandroidapp.domain.ArticleDto

@Composable
fun PullToRefreshComponent(
    items: List<ArticleDto>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onItemClick: (ArticleDto) -> Unit,
    modifier: Modifier = Modifier
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier
    ) {
        LazyColumn(
            modifier = modifier.background(color = Color.LightGray)
        ) {
            items(items) { it ->
                ArticleComponent(
                    it.title,
                    it.contentExcerpt,
                    it.imageUrl
                ) { onItemClick(it) }
            }
        }
    }
}