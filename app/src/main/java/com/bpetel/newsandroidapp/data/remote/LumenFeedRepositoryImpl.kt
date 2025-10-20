package com.bpetel.newsandroidapp.data.remote

import com.bpetel.newsandroidapp.data.model.toDomain
import com.bpetel.newsandroidapp.domain.ArticleDto
import com.bpetel.newsandroidapp.domain.LumenFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class LumenFeedRepositoryImpl(
    private val api: LumenFeedApi
) : LumenFeedRepository {

    override suspend fun getArticles(): Flow<NetworkResult<List<ArticleDto>>> {
        val articleDtoList = mutableListOf<ArticleDto>()

        return flow {
            try {
                api.getArticles().body()?.data?.forEach { article ->
                    articleDtoList.add(article.toDomain())
                }
                emit(NetworkResult.Success(articleDtoList))
            } catch (e: HttpException) {
                emit(NetworkResult.Error(e.message, e.code()))
            } catch (e: IOException) {
                emit(NetworkResult.Error( e.message))
            } catch (e: Exception) {
                emit(NetworkResult.Error(e.message))
            }
        }
    }
}