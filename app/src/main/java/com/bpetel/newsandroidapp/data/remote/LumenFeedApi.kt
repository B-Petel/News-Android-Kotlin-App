package com.bpetel.newsandroidapp.data.remote

import com.bpetel.newsandroidapp.data.model.ArticleListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface LumenFeedApi {
    @GET("articles")
    suspend fun getArticles(@Header("Authorization") authorization: String?): Response<ArticleListDto>
}
