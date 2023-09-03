package com.satyajit.newsappnew.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.satyajit.newsappnew.NewsApp
import com.satyajit.newsappnew.navigation.RootNavHost
import com.satyajit.newsappnew.ui.theme.NewsAppNewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val applicationComponent = (application as NewsApp).applicationComponent

        setContent {
            NewsAppNewTheme {
                RootNavHost(
                    navHostController = rememberNavController(),
                    applicationComponent = applicationComponent
                )
            }
        }

    }
}

