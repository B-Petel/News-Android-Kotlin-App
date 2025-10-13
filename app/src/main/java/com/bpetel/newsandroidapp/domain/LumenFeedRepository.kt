package com.bpetel.newsandroidapp.domain

import com.bpetel.newsandroidapp.data.model.ArticleListDto
import kotlinx.coroutines.flow.Flow

interface LumenFeedRepository {
    suspend fun getArticles(): Flow<ArticleListDto>

    suspend fun getArticleFilterByLanguage(language: String): Flow<ArticleListDto>
}