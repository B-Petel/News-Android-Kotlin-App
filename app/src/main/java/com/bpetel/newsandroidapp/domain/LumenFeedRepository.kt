package com.bpetel.newsandroidapp.domain

import kotlinx.coroutines.flow.Flow

interface LumenFeedRepository {
    suspend fun getArticles(): Flow<List<ArticleDto>>
}