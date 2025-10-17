package com.bpetel.newsandroidapp.data.model

import com.bpetel.newsandroidapp.domain.ArticleDto
import com.google.gson.annotations.SerializedName
import kotlin.String

data class Article(
    val id: String,
    val title: String,
    val author: String?,
    val country: String?,
    val language: String?,
    val keywords: List<String>?,
    @SerializedName("content_excerpt")
    val contentExcerpt: String?,
    @SerializedName("full_content_html")
    val fullContentHtml: String?,
    @SerializedName("full_content_sanitized")
    val fullContentSanitized: String?,
    @SerializedName("publisher_id")
    val publisherId: String,
    @SerializedName("topic_id")
    val topicId: String?,
    @SerializedName("sentiment_label")
    val sentimentLabel: String?,
    @SerializedName("sentiment_score")
    val sentimentScore: Float?,
    @SerializedName("has_video")
    val hasVideo: Boolean,
    @SerializedName("published_at")
    val publishedAt: Int,
    @SerializedName("source_link")
    val sourceLink: String,
    @SerializedName("image_url")
    val imageUrl: String?
)

fun Article.toDomain(): ArticleDto {
    return ArticleDto(
        id = id,
        publisherId = publisherId,
        author = author,
        title = title,
        contentExcerpt = contentExcerpt,
        fullContentSanitized = fullContentSanitized,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        sentimentLabel = sentimentLabel,
        sentimentScore = sentimentScore
    )
}