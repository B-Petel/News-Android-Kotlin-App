package com.bpetel.newsandroidapp.data.remote

import com.bpetel.newsandroidapp.data.model.ArticleSearchResponse
import com.google.gson.Gson
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.context.stopKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.apter.junit.jupiter.robolectric.RobolectricExtension

@ExtendWith(RobolectricExtension::class)
class LumenFeedAPITest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: LumenFeedApi
    private val content = Helper.readFileResource("/response.json")

    @BeforeEach
    fun beforeEach() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpInterceptor())
            .build()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/v1/"))//Pass any base url like this
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LumenFeedApi::class.java)
    }

    @AfterEach
    fun afterEach() {
        mockWebServer.shutdown()
        stopKoin()
    }

    @Test
    fun testGetArticles_returnArticles() = runTest {
        val articleList = Gson().fromJson(content, ArticleSearchResponse::class.java)
        mockWebServer.enqueue(getMockResponse(content, 200))

        val response = api.getArticles()
        val request = mockWebServer.takeRequest()

        assertEquals(200, response.code())
        assertEquals("/v1/articles", request.requestUrl?.encodedPath)
        assertNotNull(request.headers["X-API-Key"])
        assertEquals(false, response.body()?.data?.isEmpty())
        assertEquals(1, response.body()?.data?.size)
        assertEquals(articleList.data, response.body()?.data)
    }

    @Test
    fun testGetArticles_returnUnauthorized() = runTest {
        mockWebServer.enqueue(getMockResponse(content, 400))

        val response = api.getArticles()
        assertEquals(400, response.code())
    }

    @Test
    fun testGetArticles_returnIOException() = runTest {
        
    }

    fun getMockResponse(content: String, code: Int): MockResponse {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(code)
        mockResponse.setBody(content)

        return mockResponse
    }
}