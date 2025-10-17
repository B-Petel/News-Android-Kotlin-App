package com.bpetel.newsandroidapp.data.model

import com.google.gson.annotations.SerializedName

data class Meta(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("query_time_ms")
    val queryTimeMs: Int,
    @SerializedName("total_hits")
    val totalHits: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)