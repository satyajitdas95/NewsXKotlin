package com.satyajit.newsappnew.ui.screenhome

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.navigation.HomeScreenNavGraph

@Composable
fun HomeScreen(
    applicationComponent: ApplicationComponent,
    onNavigateSearch: () -> Unit,
    navHostController: NavHostController = rememberNavController()
) {

    Scaffold(
        topBar = {
            TopAppBarComponent(navController = navHostController, onNavigateSearch)
        },
        bottomBar = { BottomBar(navHostController = navHostController) })
    { innerPadding ->

        Surface(modifier = Modifier.padding(innerPadding)) {
            HomeScreenNavGraph(
                navController = navHostController,
                applicationComponent = applicationComponent, onNavigateSearch
            )
        }

    }
}




