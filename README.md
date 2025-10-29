<h1 align="center">News Android Application</h1>

## ScreenShot
<img width="500" height="1400" alt="NewsFeed" src="https://github.com/user-attachments/assets/71c7d323-0466-4b3e-946b-a50afadea6b9" /> <img width="500" height="1400" alt="NewsDetails" src="https://github.com/user-attachments/assets/cb9fdd57-895d-4e23-9f89-ff55dc25375c" />

## Architecture
This App use a Clean Architecture with a Domain-Centric pattern


<h1 align="center">
	<img width="600" height="1214" alt="Architecture" src="https://github.com/user-attachments/assets/1b64f538-74d4-42dd-b990-82f2a56e8a53" />
</h1> 


## Data
+ The API used is [LumenFeed API](https://lumenfeed.com/)
+ This is a Restful API implemented with Retrofit 2

First we create an instance of the API class inside our app module using Koin 

```
val appModule = module {
	interface LumenFeedApi {
    		@GET("articles")
    		suspend fun getArticles(
        		@Query("filter_by") filter: String
    		): Response<ArticleSearchResponse>
	}
}

```

```
interface LumenFeedApi {
    @GET("articles")
    suspend fun getArticles(
        @Query("filter_by") filter: String
    ): Response<ArticleSearchResponse>
}
```

We then create our Repository implementation inside our data layer, which will extend the Repository interface inside our Domain layer.
That way we insure that the data layer doesn't have access to our Domain layer

```

class LumenFeedRepositoryImpl(
    private val api: LumenFeedApi
) : LumenFeedRepository {

    override suspend fun getArticles(
        filter: String
    ): Flow<NetworkResult<List<ArticleDto>>> {
        val articleDtoList = mutableListOf<ArticleDto>()

        return flow {
            try {
                api.getArticles(filter).body()?.data?.forEach { article ->
                    article.imageUrl?.let {
                        articleDtoList.add(article.toDomain())
                    }
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
```

```
interface LumenFeedRepository {
    suspend fun getArticles(
        filter: String
    ): Flow<NetworkResult<List<ArticleDto>>>
}
```
