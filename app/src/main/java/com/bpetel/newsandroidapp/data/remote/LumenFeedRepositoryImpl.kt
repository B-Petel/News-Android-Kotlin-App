package com.bpetel.newsandroidapp.data.remote

import com.bpetel.newsandroidapp.data.model.toDomain
import com.bpetel.newsandroidapp.domain.ArticleDto
import com.bpetel.newsandroidapp.domain.LumenFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LumenFeedRepositoryImpl(
    private val api: LumenFeedApi
) : LumenFeedRepository {
    override suspend fun getArticles(): Flow<List<ArticleDto>> {
        val articleDtoList = mutableListOf<ArticleDto>()

        api.getArticles().body()?.data?.forEach { article ->
            articleDtoList.add(article.toDomain())
        }

        return flow {
            emit(articleDtoList)
        }
    }
}