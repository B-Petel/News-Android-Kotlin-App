package com.bpetel.newsandroidapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bpetel.newsandroidapp.presentation.screen.DetailsScreen
import com.bpetel.newsandroidapp.presentation.screen.MainScreen
import com.bpetel.newsandroidapp.ui.theme.NewsAndroidAppTheme

class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAndroidAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


    @Composable
    fun Navigation(
        modifier: Modifier
    ){
        // important part and must declare fisrt
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.MainScreen // Set Screen to appear for the first time
        ){
            // Declare all Screen in here
            composable<Screen.MainScreen>{
                MainScreen(
                    modifier = modifier,
                    onItemClick = { article -> navController.navigate(Screen.DetailScreen(article.id)) }
                )
            }
            composable<Screen.DetailScreen>{
                DetailsScreen(articleId = it.id)
            }
        }
    }
}