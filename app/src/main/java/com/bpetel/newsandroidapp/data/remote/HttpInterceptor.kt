package com.bpetel.newsandroidapp.data.remote

import com.bpetel.newsandroidapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-API-Key", BuildConfig.API_KEY)
            .build()

        return chain.proceed(request)
    }
}