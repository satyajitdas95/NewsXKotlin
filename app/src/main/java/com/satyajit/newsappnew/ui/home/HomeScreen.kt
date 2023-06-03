package com.satyajit.newsappnew.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.navigation_graph.HomeScreenNavGraph

@Composable
fun HomeScreen(
    applicationComponent: ApplicationComponent,
    navHostController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            TopAppBarComponent(navController = navHostController)
        },
        bottomBar = { BottomBar(navHostController = navHostController) })
    { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            HomeScreenNavGraph(
                navController = navHostController,
                applicationComponent = applicationComponent
            )
        }

    }
}




