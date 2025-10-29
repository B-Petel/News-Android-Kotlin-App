package com.bpetel.newsandroidapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.bpetel.newsandroidapp.domain.ArticleDto
import com.bpetel.newsandroidapp.presentation.articles.ArticleDetailsScreen
import com.bpetel.newsandroidapp.presentation.articles.ArticlesFeedScreen

@Composable
fun Navigation(
    modifier: Modifier
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.ArticlesFeedScreen
    ){
        composable<Screen.ArticlesFeedScreen>{
            ArticlesFeedScreen(
                modifier = modifier,
                onItemClick = { article -> navController.navigate(article) }
            )
        }

        composable<ArticleDto> {
            val args: ArticleDto = it.toRoute()
            ArticleDetailsScreen(
                modifier = modifier,
                articleDto = args,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}