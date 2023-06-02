package com.satyajit.newsappnew.navigation_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.satyajit.newsappnew.data.local.countryList
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.ui.country.CountryScreen
import com.satyajit.newsappnew.ui.news_details.NewsDetailsScreen
import com.satyajit.newsappnew.ui.top_head_line.TopHeadLinesRoute


@Composable
fun HomeScreenNavGraph(
    navController: NavHostController,
    applicationComponent: ApplicationComponent
) {
    NavHost(navController = navController, startDestination = HomeNavGraph.TopNews.route) {

        composable(HomeNavGraph.TopNews.route) {
            TopHeadLinesRoute(
                onClickOfNewsITem = { newslink -> navController.navigate("newsdetails/?url=$newslink") },
                applicationComponent,
            )
        }

        composable(HomeNavGraph.DetailsOfNews.route,arguments = listOf(
            navArgument("newsDetailsUrl") {
                type = NavType.StringType
            }
        )) {
            val newsUrl = it.arguments?.getString("newsDetailsUrl") ?: ""
            NewsDetailsScreen(newsUrl)
        }

        composable(HomeNavGraph.Sources.route) {

        }

        composable(HomeNavGraph.Country.route) {
            CountryScreen(countryList, onClickOfCountry = {})
        }

        composable(HomeNavGraph.Language.route) {

        }


    }


//

//
//    composable(ROUTE_NEWS_DETAILS) {
//        val newsUrl = it.arguments?.getString("newsLink")
//        val intent = CustomTabsIntent.Builder()
//            .build()
//        intent.launchUrl(applicationComponent.getContext(), Uri.parse(newsUrl))
//
////            NewsDetailsRoute(
////                newsUrl,
////                navHostController = navController
////            )
//    }


}

sealed class HomeNavGraph(val route: String) {
    object TopNews : HomeNavGraph("topnews")
    object Sources : HomeNavGraph("sources")
    object Country : HomeNavGraph("country")
    object Language : HomeNavGraph("language")
    object DetailsOfNews : HomeNavGraph("newsdetails/?url={newsDetailsUrl}")
}


