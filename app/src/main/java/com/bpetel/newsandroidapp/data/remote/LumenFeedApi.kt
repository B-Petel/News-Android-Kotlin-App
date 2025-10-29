package com.bpetel.newsandroidapp.data.remote

import com.bpetel.newsandroidapp.data.model.ArticleSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LumenFeedApi {
    @GET("articles")
    suspend fun getArticles(
        @Query("filter_by") filter: String
    ): Response<ArticleSearchResponse>
}
