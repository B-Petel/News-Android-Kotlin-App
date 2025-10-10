package com.bpetel.newsandroidapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        println("we got: ${mainViewModel.getFrenchArticles().data.size}")

    }
}