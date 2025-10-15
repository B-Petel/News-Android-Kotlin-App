package com.bpetel.newsandroidapp.domain

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: String = "",
    val publisherId: String = "",
    val author: String = "",
    val title: String = "",
    val contentExcerpt: String = "",
    val fullContentSanitized: String? = "",
    val imageUrl: String = "",
    val publishedAt: Int = 0,
    val sentimentLabel: String = "",
    val sentimentScore: Double = 0.0
)
