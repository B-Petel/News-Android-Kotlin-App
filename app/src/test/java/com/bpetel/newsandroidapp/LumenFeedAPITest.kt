package com.bpetel.newsandroidapp

import com.bpetel.newsandroidapp.data.remote.HttpInterceptor
import com.bpetel.newsandroidapp.data.remote.LumenFeedApi
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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.apter.junit.jupiter.robolectric.RobolectricExtension

@ExtendWith(RobolectricExtension::class)
class LumenFeedAPITest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: LumenFeedApi

    @BeforeEach//Using JUnit5
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
    }

    @Test
    fun testGetArticles_returnArticles() = runTest {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = api.getArticles()
        val request = mockWebServer.takeRequest()

        assertEquals("/v1/articles", request.requestUrl?.encodedPath)
        assertNotNull(request.headers["X-API-Key"])
        assertEquals(false, response.body()?.data?.isEmpty())
        assertEquals(1, response.body()?.data?.size)
    }
}