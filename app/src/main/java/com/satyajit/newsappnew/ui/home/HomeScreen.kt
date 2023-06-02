package com.satyajit.newsappnew.ui.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.navigation_graph.HomeScreenNavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
    {

        HomeScreenNavGraph(
            navController = navHostController,
            applicationComponent = applicationComponent
        )
    }
}




