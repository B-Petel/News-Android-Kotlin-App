package com.bpetel.newsandroidapp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.bpetel.newsandroidapp.R
import com.bpetel.newsandroidapp.domain.ArticleDto
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DetailsScreen(
    modifier: Modifier,
    articleDto: ArticleDto,
    onBackClick: () -> Unit
) {

    val state = rememberScrollState()

    Column(
        modifier = modifier.fillMaxSize().verticalScroll(state)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .align (Alignment.TopStart),
                model = articleDto.imageUrl,
                contentDescription = null
            )

            Button(
                modifier = Modifier.padding(2.dp),
                colors = buttonColors(containerColor = Color.White, contentColor = Color.Black),
                onClick = { onBackClick() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_action_name),
                    contentDescription = ""
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = articleDto.publisherId,
                color = Color.DarkGray,
                fontSize = 10.sp,
                fontStyle = FontStyle.Italic
            )

            articleDto.author?.let {
                if (it.isNotBlank())
                Text(
                    text = " : " + articleDto.author,
                    fontSize = 10.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.DarkGray
                )
            }
        }

        val sdf = SimpleDateFormat("dd/MM/yy hh:mm a", Locale.FRANCE)
        val date = Date(articleDto.publishedAt.toLong() * 1000)
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = sdf.format(date),
            fontSize = 10.sp,
            color = Color.DarkGray
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            text = articleDto.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )

        articleDto.fullContentSanitized?.let {
            Text(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                text = articleDto.fullContentSanitized
            )
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        modifier = Modifier.fillMaxSize(),
        ArticleDto(
            id = "",
            title = "Title",
            fullContentSanitized = stringResource(R.string.lorem_ipsum),
            author = "Author",
            publisherId = "",
            contentExcerpt = "",
            imageUrl = "",
            publishedAt = 0,
            sentimentLabel = "",
            sentimentScore = 0f
        )
    ) {}
}