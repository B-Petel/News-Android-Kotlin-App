package com.bpetel.newsandroidapp.presentation.articles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bpetel.newsandroidapp.ui.theme.NewsPaperTertiary

@Composable
fun ArticleComponent(
    title: String,
    summary: String?,
    img: String?,
    onItemClick: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{ onItemClick() },
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {

                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium
                )
                summary?.let {
                    Text(
                        text = summary,
                        textAlign = TextAlign.Justify,
                        color = NewsPaperTertiary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            img?.let {
                AsyncImage(
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp),
                    model = img,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
            }
        }

    }
    HorizontalDivider(
        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
        thickness = 2.dp,
        color = NewsPaperTertiary
    )
}
