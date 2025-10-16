package com.bpetel.newsandroidapp

import com.bpetel.newsandroidapp.data.model.ArticleListDto
import com.bpetel.newsandroidapp.data.remote.LumenFeedApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.test.runTest
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

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/v1/"))//Pass any base url like this
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LumenFeedApi::class.java)
    }

    @AfterEach
    fun afterEach() {
        mockWebServer.shutdown()
    }

//    @Test
//    fun `getArticles, returns Success`() = runTest {
//        val dto = ArticleListDto()//The object I want back as response
//        val gson: Gson = GsonBuilder().create()
//        val json = gson.toJson(dto)!!//Conver the object into json string using GSON
//        val res = MockResponse()//Make a fake response for our server call
//        res.setBody(json)//set the body of the fake response as the json string you are expecting as a response
//        mockWebServer.enqueue(res)//add it in the server response queue
//
//        val data = api.getArticles()//make the call to our fake server(as we are using fake base url)
//        mockWebServer.takeRequest()//let the server take the request
//
//        assertEquals(data, dto)//the data you are getting as the call response should be same
//    }


    @Test
    fun testGetArticles_returnArticles() = runTest {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = api.getArticles(BuildConfig.API_KEY)
        val request = mockWebServer.takeRequest()

        assertEquals("/v1/articles", request.requestUrl?.encodedPath)
        assertNotNull(request.headers["Authorization"])
        assertEquals(false, response.body()?.data?.isEmpty())
        assertEquals(1, response.body()?.data?.size)
    }
}