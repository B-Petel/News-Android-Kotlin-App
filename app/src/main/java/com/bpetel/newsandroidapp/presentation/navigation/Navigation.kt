package com.bpetel.newsandroidapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.bpetel.newsandroidapp.domain.ArticleDto
import com.bpetel.newsandroidapp.presentation.screen.DetailsScreen
import com.bpetel.newsandroidapp.presentation.screen.MainScreen

@Composable
fun Navigation(
    modifier: Modifier
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen
    ){
        composable<Screen.MainScreen>{
            MainScreen(
                modifier = modifier,
                onItemClick = { article -> navController.navigate(article) }
            )
        }

        composable<ArticleDto> {
            val args: ArticleDto = it.toRoute()
            DetailsScreen(
                modifier = modifier,
                articleDto = args,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}