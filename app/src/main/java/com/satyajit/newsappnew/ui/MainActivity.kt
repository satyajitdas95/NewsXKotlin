package com.satyajit.newsappnew.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.satyajit.newsappnew.NewsApp
import com.satyajit.newsappnew.navigation.MyAppNavHost
import com.satyajit.newsappnew.ui.theme.NewsAppNewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val applicationCoponent = (application as NewsApp).applicationComponent

        setContent {
            NewsAppNewTheme {
                MyAppNavHost(applicationComponent = applicationCoponent)
            }
        }

    }
}
