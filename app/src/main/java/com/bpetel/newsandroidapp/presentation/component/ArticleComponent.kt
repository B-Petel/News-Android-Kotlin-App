package com.bpetel.newsandroidapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bpetel.newsandroidapp.domain.Article
import com.bpetel.newsandroidapp.ui.theme.NewsAndroidAppTheme

@Composable
fun ArticleComponent(
    article: Article,
    title: String?,
    content: String?,
    author: String?,
    img: String?,
    onItemClick: (Article) -> Unit) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable{ onItemClick(article) },
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier.clip(RoundedCornerShape(5.dp)),
                model = img,
                contentDescription = null
            )
            if (title != null)
            Text(
                modifier = Modifier.padding(5.dp),
                text = title, maxLines = 2,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium
            )

            if (content != null)
            Text(modifier = Modifier.padding(5.dp), text = content, maxLines = 1)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ArticleComponentPreview() {
//    NewsAndroidAppTheme {
//        ArticleComponent("Title", "Content", "Author", "https://home.moe.gov.om/templates/moe/assets/images/logo.png")
//    }
//}