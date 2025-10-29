package com.bpetel.newsandroidapp.presentation.articles

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bpetel.newsandroidapp.domain.ArticleDto
import com.bpetel.newsandroidapp.presentation.UIState
import com.bpetel.newsandroidapp.presentation.UiEvent
import com.bpetel.newsandroidapp.ui.theme.AppTitle
import com.bpetel.newsandroidapp.ui.theme.NewsPaperSecondary
import com.bpetel.newsandroidapp.ui.theme.NewsPaperTertiary
import org.koin.compose.viewmodel.koinViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticlesFeedScreen(
    modifier: Modifier,
    articleViewModel: ArticleViewModel = koinViewModel(),
    onItemClick: (ArticleDto) -> Unit
) {
    var isRefreshing by remember { mutableStateOf(false) }
    val sdf = SimpleDateFormat("EEE, MMMM - dd - yyyy", Locale.UK)

    val dayString = LocalDate.now().dayOfWeek.toString()
    val day = LocalDate.now().dayOfMonth.toString()
    val month = LocalDate.now().month.toString()
    val year = LocalDate.now().year.toString()

    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NewsPaperTertiary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = AppTitle,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Medium
                )
            }

            Text(
                text = "${dayString.substring(0,3)}, $month $day"
            )
        }

        articleViewModel.uiState.collectAsState().value.let { state ->
            when(state) {
                is UIState.Error ->
                    println("UIState.Error : " + state.error)
                UIState.Loading ->
                    println("UIState.Loading")
                is UIState.Success ->
                    PullToRefreshBox(
                        isRefreshing = isRefreshing,
                        onRefresh = {
                            isRefreshing = true
                            articleViewModel.onEvent(UiEvent.Refresh)
                            isRefreshing = false
                        },
                    ) {
                        LazyColumn {
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
    }
}

