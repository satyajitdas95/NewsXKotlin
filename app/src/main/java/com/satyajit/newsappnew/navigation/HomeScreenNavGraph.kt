package com.satyajit.newsappnew.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.satyajit.newsappnew.data.local.countryList
import com.satyajit.newsappnew.data.local.languageList
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.ui.screencountry.CountryScreen
import com.satyajit.newsappnew.ui.screenlanguage.LanguageScreen
import com.satyajit.newsappnew.ui.screennewsdetails.NavigateToNewsDetails
import com.satyajit.newsappnew.ui.screensearch.SearchRoot
import com.satyajit.newsappnew.ui.screensource.SourceScreenRoute
import com.satyajit.newsappnew.ui.screenspecificnewslist.NewsType
import com.satyajit.newsappnew.ui.screenspecificnewslist.SpecificNewsRoute
import com.satyajit.newsappnew.ui.screentopheadline.TopHeadLinesRoute


@Composable
fun HomeScreenNavGraph(
    navController: NavHostController,
    applicationComponent: ApplicationComponent,
    onNavigateSearch: () -> Unit
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
            val context = LocalContext.current
            LaunchedEffect(key1 = newsUrl, block = {
                NavigateToNewsDetails(context, newsUrl)
                navController.popBackStack()
            })
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
            LanguageScreen(
                languageList,
                onClickOfLanguage = { languageCode -> navController.navigate("specificNewsList/?language=$languageCode") })
        }

        composable(HomeNavGraph.SearchScreen.route) {
            SearchRoot(
                applicationComponent = applicationComponent,
                onClickOfNewsITem = { newslink -> navController.navigate("newsDetails/?url=$newslink") },
                onNavigateBack = { navController.popBackStack() })
        }

        composable(
            HomeNavGraph.NewsListByCountry.route,
            arguments = listOf(navArgument("countryCode") {
                defaultValue = null;nullable = true;type = NavType.StringType
            })
        ) {
            val countryCode = it.arguments?.getString("countryCode") ?: ""
            SpecificNewsRoute(
                newsType = NewsType.NewsByCountry,
                countryCode = countryCode,
                onClickOfNewsITem = { newsLink -> navController.navigate("newsDetails/?url=$newsLink") },
                applicationComponent = applicationComponent
            )
        }

        composable(
            HomeNavGraph.NewsListBySource.route,
            arguments = listOf(navArgument("sources") {
                defaultValue = null; nullable = true; type = NavType.StringType
            })
        ) {
            val sources = it.arguments?.getString("sources") ?: ""
            SpecificNewsRoute(
                newsType = NewsType.NewsBySource, sources = sources,
                onClickOfNewsITem = { newsLink -> navController.navigate("newsDetails/?url=$newsLink") },
                applicationComponent = applicationComponent
            )
        }

        composable(
            HomeNavGraph.NewsListByLanguage.route,
            arguments = listOf(navArgument("language") {
                defaultValue = null; nullable = true; type = NavType.StringType
            })
        ) {
            val sources = it.arguments?.getString("language") ?: ""
            SpecificNewsRoute(
                newsType = NewsType.NewsBySource, sources = sources,
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
    object NewsListByCountry : HomeNavGraph("specificNewsList/?countryCode={countryCode}")
    object NewsListBySource : HomeNavGraph("specificNewsList/?&sources={sources}")
    object NewsListByLanguage : HomeNavGraph("specificNewsList/?&language={language}")
    object SearchScreen : HomeNavGraph("search")
}

