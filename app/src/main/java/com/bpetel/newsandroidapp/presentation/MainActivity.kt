package com.bpetel.newsandroidapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.bpetel.newsandroidapp.presentation.navigation.Navigation
import com.bpetel.newsandroidapp.ui.theme.NewsAndroidAppTheme
import com.bpetel.newsandroidapp.ui.theme.NewsPaperPrimary

class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAndroidAppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    containerColor = NewsPaperPrimary
                ) { innerPadding ->
                    Navigation(
                        modifier = Modifier
                            .padding(innerPadding)

                    )
                }
            }
        }
    }
}