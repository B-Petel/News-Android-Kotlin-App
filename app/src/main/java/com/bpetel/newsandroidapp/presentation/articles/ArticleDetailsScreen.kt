package com.bpetel.newsandroidapp.presentation.articles

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.bpetel.newsandroidapp.R
import com.bpetel.newsandroidapp.domain.ArticleDto
import com.bpetel.newsandroidapp.ui.theme.NewsPaperTertiary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ArticleDetailsScreen(
    modifier: Modifier,
    articleDto: ArticleDto,
    onBackClick: () -> Unit
) {

    val state = rememberScrollState()

    val publishedDate = SimpleDateFormat("EEE, MMMM - dd - yyyy", Locale.UK)
    val publishedHour = SimpleDateFormat("hh:mm", Locale.UK)
    val date = Date(articleDto.publishedAtInMs)

    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .border(width = 1.dp, color = NewsPaperTertiary, shape = RectangleShape),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(state)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable(onClick = onBackClick),
                    painter = painterResource(R.drawable.ic_action_name),
                    contentDescription = ""
                )
            }
            articleDto.imageUrl?.let {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = articleDto.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = publishedDate.format(date) + " at " + publishedHour.format(date),
                fontSize = 10.sp,
                color = Color.DarkGray
            )


            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = articleDto.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Justify
            )
            articleDto.fullContentSanitized?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    text = articleDto.fullContentSanitized,
                    textAlign = TextAlign.Justify
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                articleDto.author?.let {
                    if (it.isNotBlank())
                        Text(
                            text = articleDto.author,
                            fontSize = 10.sp,
                            fontStyle = FontStyle.Italic,
                            color = Color.DarkGray
                        )
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    ArticleDetailsScreen(
        modifier = Modifier.fillMaxSize(),
        ArticleDto(
            id = "",
            title = "Title",
            fullContentSanitized = stringResource(R.string.lorem_ipsum),
            author = "Author",
            publisherId = "",
            contentExcerpt = "",
            imageUrl = "",
            publishedAtInMs = 0,
            sentimentLabel = "",
            sentimentScore = 0f
        )
    ) {}
}