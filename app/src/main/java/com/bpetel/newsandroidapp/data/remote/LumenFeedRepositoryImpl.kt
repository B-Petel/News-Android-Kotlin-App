package com.bpetel.newsandroidapp.data.remote

import com.bpetel.newsandroidapp.data.model.ArticleListDto
import com.bpetel.newsandroidapp.domain.LumenFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LumenFeedRepositoryImpl(
    private val api: LumenFeedApi
) : LumenFeedRepository {
    override suspend fun getArticles(): Flow<ArticleListDto> {
        return flow {
            emit(api.getArticles())
        }
    }

    override suspend fun getArticleFilterByLanguage(language: String): Flow<ArticleListDto> {
        return flow {
            emit(api.getArticlesFilterByCountry(language))
        }
    }
}