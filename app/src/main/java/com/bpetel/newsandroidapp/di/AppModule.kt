package com.bpetel.newsandroidapp.di

import com.bpetel.newsandroidapp.data.remote.HttpInterceptor
import com.bpetel.newsandroidapp.data.remote.LumenFeedApi
import com.bpetel.newsandroidapp.data.remote.LumenFeedRepositoryImpl
import com.bpetel.newsandroidapp.domain.LumenFeedRepository
import com.bpetel.newsandroidapp.presentation.MainViewModel
import com.bpetel.newsandroidapp.utils.Constants.Companion.BASE_URL
import com.bpetel.newsandroidapp.utils.Constants.Companion.DEBUG_URL
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.new
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {

        val client = OkHttpClient.Builder().apply {
            addInterceptor (HttpInterceptor())
        }.build()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LumenFeedApi::class.java)
    }

    single<LumenFeedRepository> {
        LumenFeedRepositoryImpl(get())
    }

    viewModel { MainViewModel(get()) }
//    viewModelOf(::MainViewModel(get()))
}