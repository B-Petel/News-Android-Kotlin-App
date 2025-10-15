package com.bpetel.newsandroidapp.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bpetel.newsandroidapp.domain.Article

@Composable
fun DetailsScreen(
    modifier: Modifier,
    article: Article
) {

    if (article.title != null) {
        Text(text = article.title)
    }
}