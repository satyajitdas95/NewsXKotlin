package com.satyajit.newsappnew.navigation_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.satyajit.newsappnew.data.local.countryList
import com.satyajit.newsappnew.data.local.languageList
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.ui.screen_country.CountryScreen
import com.satyajit.newsappnew.ui.screen_language.LanguageScreen
import com.satyajit.newsappnew.ui.screen_news_details.NewsDetailsScreen
import com.satyajit.newsappnew.ui.screen_source.SourceScreenRoute
import com.satyajit.newsappnew.ui.screen_specific_news_list.SpecificNewsRoute
import com.satyajit.newsappnew.ui.screen_top_head_line.TopHeadLinesRoute


@Composable
fun HomeScreenNavGraph(
    navController: NavHostController,
    applicationComponent: ApplicationComponent
) {
    NavHost(navController = navController, startDestination = HomeNavGraph.TopNews.route) {

        composable(HomeNavGraph.TopNews.route) {
            TopHeadLinesRoute(
                onClickOfNewsITem = { newslink -> navController.navigate("newsDetails/?url=$newslink") },
                applicationComponent,
            )
        }

        composable(HomeNavGraph.DetailsOfNews.route, arguments = listOf(
            navArgument("newsDetailsUrl") {
                type = NavType.StringType
            }
        )) {
            val newsUrl = it.arguments?.getString("newsDetailsUrl") ?: ""
            NewsDetailsScreen(newsUrl, navController)
        }

        composable(HomeNavGraph.Sources.route) {
            SourceScreenRoute(
                applicationComponent = applicationComponent,
                onClickOfSource = { sourceName -> navController.navigate("specificNewsList/?sources=$sourceName") })
        }

        composable(HomeNavGraph.Country.route) {
            CountryScreen(
                countryList,
                onClickOfCountry = { countryCode -> navController.navigate("specificNewsList/?countryCode=$countryCode") })
        }

        composable(HomeNavGraph.Language.route) {
            LanguageScreen(languageList, onClickOfLanguage = {})
        }

        composable(
            HomeNavGraph.SpecificNewsList.route,
            arguments = listOf(navArgument("countryCode") {
                defaultValue = null
                nullable = true
                type = NavType.StringType
            }, navArgument("sources") {
                defaultValue = null
                nullable = true
                type = NavType.StringType
            })
        ) {
            val countryCode = it.arguments?.getString("countryCode") ?: ""
            val sources = it.arguments?.getString("sources") ?: ""
            SpecificNewsRoute(
                countryCode,
                sources,
                onClickOfNewsITem = { newsLink -> navController.navigate("newsDetails/?url=$newsLink") },
                applicationComponent = applicationComponent
            )
        }


    }

}

sealed class HomeNavGraph(val route: String) {
    object TopNews : HomeNavGraph("topnews")
    object Sources : HomeNavGraph("sources")
    object Country : HomeNavGraph("country")
    object Language : HomeNavGraph("language")
    object DetailsOfNews : HomeNavGraph("newsDetails/?url={newsDetailsUrl}")
    object SpecificNewsList :
        HomeNavGraph("specificNewsList/?countryCode={countryCode}&sources={sources}")
}


