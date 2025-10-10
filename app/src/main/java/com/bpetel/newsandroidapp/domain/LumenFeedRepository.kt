package com.bpetel.newsandroidapp.domain

import com.bpetel.newsandroidapp.data.model.ArticleListDto

interface LumenFeedRepository {
    suspend fun getArticles(): ArticleListDto
}