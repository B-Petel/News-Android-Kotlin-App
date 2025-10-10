package com.bpetel.newsandroidapp.data.model

data class ArticleDto(
    val author: String,
    val content_excerpt: String,
    val country: String,
    val full_content_html: String,
    val full_content_sanitized: String,
    val has_video: Boolean,
    val id: String,
    val image_url: String,
    val keywords: List<String>,
    val language: String,
    val published_at: Int,
    val publisher_id: String,
    val sentiment_label: String,
    val sentiment_score: Double,
    val source_link: String,
    val title: String,
    val topic_id: String
)