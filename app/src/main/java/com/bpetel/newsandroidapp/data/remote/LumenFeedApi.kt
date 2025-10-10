package com.bpetel.newsandroidapp.data.remote

import com.bpetel.newsandroidapp.data.model.ArticleListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface LumenFeedApi {

    @GET("articles")
    suspend fun getArticles(): ArticleListDto

    @GET("articles")
    suspend fun getArticlesFilterByCountry(
        @Query("country") country: String,
    ): ArticleListDto

}
