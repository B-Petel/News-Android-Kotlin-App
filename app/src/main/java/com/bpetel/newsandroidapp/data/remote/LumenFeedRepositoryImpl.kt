package com.bpetel.newsandroidapp.data.remote

import com.bpetel.newsandroidapp.data.model.ArticleDto
import com.bpetel.newsandroidapp.data.model.ArticleListDto
import com.bpetel.newsandroidapp.domain.LumenFeedRepository

class LumenFeedRepositoryImpl(
    private val api: LumenFeedApi
) : LumenFeedRepository {
    override suspend fun getArticles(): ArticleListDto {
        return api.getArticles()
        TODO("Not yet implemented")
    }


}