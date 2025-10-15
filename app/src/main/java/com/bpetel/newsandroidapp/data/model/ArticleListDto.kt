package com.bpetel.newsandroidapp.data.model

data class ArticleListDto(
    val data: List<ArticleDto> = emptyList(),
    val meta: MetaDataDto = MetaDataDto()
)