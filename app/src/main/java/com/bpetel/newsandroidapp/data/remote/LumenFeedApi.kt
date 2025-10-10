package com.bpetel.newsandroidapp.data.remote

import com.bpetel.newsandroidapp.data.model.ArticleListDto
import retrofit2.http.GET

interface LumenFeedApi {

    @GET("articles")
    suspend fun getArticles(): ArticleListDto
}
