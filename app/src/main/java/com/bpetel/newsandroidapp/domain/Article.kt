package com.bpetel.newsandroidapp.domain

data class Article(
    val id: String,
    val publisherId: String?,
    val author: String?,
    val title: String?,
    val contentExcerpt: String?,
    val fullContentSanitized: String?,
    val imageUrl: String?,
    val publishedAt: Int?,
    val sentimentLabel: String?,
    val sentimentScore: Double?
)
