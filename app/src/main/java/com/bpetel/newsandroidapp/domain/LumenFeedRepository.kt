package com.bpetel.newsandroidapp.domain

import com.bpetel.newsandroidapp.data.model.ArticleDto
import com.bpetel.newsandroidapp.data.model.ArticleListDto

interface LumenFeedRepository {
    suspend fun getArticles(): ArticleListDto

    suspend fun getArticleFilterByLanguage(language: String): ArticleListDto
}