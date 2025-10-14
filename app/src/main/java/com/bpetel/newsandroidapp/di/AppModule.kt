package com.bpetel.newsandroidapp.di

import com.bpetel.newsandroidapp.data.remote.HttpInterceptor
import com.bpetel.newsandroidapp.data.remote.LumenFeedApi
import com.bpetel.newsandroidapp.data.remote.LumenFeedRepositoryImpl
import com.bpetel.newsandroidapp.domain.LumenFeedRepository
import com.bpetel.newsandroidapp.presentation.viewmodel.MainViewModel
import com.bpetel.newsandroidapp.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
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
}