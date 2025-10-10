package com.bpetel.newsandroidapp.data.model

data class MetaDataDto(
    val page: Int,
    val per_page: Int,
    val query_time_ms: Int,
    val total_hits: Int,
    val total_pages: Int
)