package com.bpetel.newsandroidapp.presentation

import androidx.lifecycle.ViewModel
import com.bpetel.newsandroidapp.data.model.ArticleDto
import com.bpetel.newsandroidapp.data.model.ArticleListDto
import com.bpetel.newsandroidapp.domain.LumenFeedRepository

class MainViewModel (
    private val repository: LumenFeedRepository
): ViewModel() {

    suspend fun getArticles(): ArticleListDto {
        return repository.getArticles()
    }

    suspend fun getFrenchArticles(): ArticleListDto {
        return repository.getArticleFilterByLanguage("en")
    }

}