package com.satyajit.newsappnew.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.ui.screenhome.HomeScreen
import com.satyajit.newsappnew.ui.screennewsdetails.NavigateToNewsDetails
import com.satyajit.newsappnew.ui.screensearch.SearchRoot
import com.satyajit.newsappnew.ui.screenspalsh.SplashScreen


@Composable
fun RootNavHost(applicationComponent: ApplicationComponent, navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Graphs.SplashScreen.route) {
        composable(Graphs.SplashScreen.route) {
            SplashScreen(
                onSplashCompleted = { navHostController.navigate(Graphs.HomeScreen.route) })
        }

        composable(Graphs.HomeScreen.route) {
            HomeScreen(
                applicationComponent,
                onNavigateSearch = { navHostController.navigate(Graphs.SearchScreen.route) })
        }

        composable(Graphs.SearchScreen.route) {
            SearchRoot(
                applicationComponent,
                onClickOfNewsITem = { newslink -> navHostController.navigate("newsDetails/?url=$newslink") },
                onNavigateBack = { navHostController.popBackStack() })
        }

        composable(Graphs.NewsDetails.route, arguments = listOf(
            navArgument("newsDetailsUrl") {
                type = NavType.StringType
            }
        )) {
            val newsUrl = it.arguments?.getString("newsDetailsUrl") ?: ""
            val context = LocalContext.current
            LaunchedEffect(key1 = newsUrl, block = {
                NavigateToNewsDetails(context, newsUrl)
                navHostController.popBackStack()
            })
        }

    }

}

sealed class Graphs(val route: String) {
    object SplashScreen : Graphs("splash")
    object HomeScreen : Graphs("homescreen")
    object SearchScreen : Graphs("searchscreen")
    object NewsDetails : Graphs("newsDetails")
}