package com.bpetel.newsandroidapp.data.remote

import com.bpetel.newsandroidapp.BuildConfig
import com.bpetel.newsandroidapp.data.model.toArticle
import com.bpetel.newsandroidapp.domain.Article
import com.bpetel.newsandroidapp.domain.LumenFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LumenFeedRepositoryImpl(
    private val api: LumenFeedApi
) : LumenFeedRepository {
    override suspend fun getArticles(): Flow<List<Article>> {
        val articleList = mutableListOf<Article>()

        api.getArticles(BuildConfig.API_KEY).body()?.data?.forEach { articleDto ->
            articleList.add(articleDto.toArticle())
        }

//        api.getArticles(BuildConfig.API_KEY).body.forEach { article ->
//                articleList.add(article.toArticle())
//            }

        return flow {
            emit(articleList)
        }
    }
}