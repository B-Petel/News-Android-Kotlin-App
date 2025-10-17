package com.bpetel.newsandroidapp.domain

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    val id: String,
    val publisherId: String,
    val author: String?,
    val title: String,
    val contentExcerpt: String?,
    val fullContentSanitized: String?,
    val imageUrl: String?,
    val publishedAt: Int,
    val sentimentLabel: String?,
    val sentimentScore: Float?
)
