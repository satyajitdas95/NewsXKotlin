package com.satyajit.newsappnew.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.ui.top_head_line.TopHeadlineScreen

const val ROUTE_TOP_HEADLINES = "topHeadLines"
const val ROUTE_NEWS_DETAILS = "newsDetails"

@Composable
fun MyAppNavHost(
    applicationComponent: ApplicationComponent,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_TOP_HEADLINES
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ROUTE_TOP_HEADLINES) {
            TopHeadlineScreen(
                applicationComponent,
                navController
            )
        }

        composable(ROUTE_NEWS_DETAILS) { ProfileScreen { println("Hello") } }
    }
}

@Composable
fun ProfileScreen(
    onClick: () -> Unit
    /*...*/
) {
    /*...*/
    Button(onClick = onClick) {
        Text(text = "See friends list")
    }
}