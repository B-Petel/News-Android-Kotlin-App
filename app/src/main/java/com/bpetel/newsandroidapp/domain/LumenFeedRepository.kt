package com.bpetel.newsandroidapp.domain

import com.bpetel.newsandroidapp.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

interface LumenFeedRepository {
    suspend fun getArticles(): Flow<NetworkResult<List<ArticleDto>>>
}