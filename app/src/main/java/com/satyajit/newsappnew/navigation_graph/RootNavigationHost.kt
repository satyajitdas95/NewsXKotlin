package com.satyajit.newsappnew.navigation_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.ui.home.HomeScreen
import com.satyajit.newsappnew.ui.spalsh.SplashScreen

@Composable
fun RootNavHost(applicationComponent: ApplicationComponent, navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Graphs.SplashScreen.route) {
        composable(Graphs.SplashScreen.route) {
            SplashScreen(
                navHostController = navHostController,
                onSplashCompleted = { navHostController.navigate(Graphs.HomeScreen.route) })
        }

        composable(Graphs.HomeScreen.route) {
            HomeScreen(applicationComponent)
        }

    }

}


sealed class Graphs(val route: String) {
    object SplashScreen : Graphs("splash")
    object HomeScreen : Graphs("homescreen")
}