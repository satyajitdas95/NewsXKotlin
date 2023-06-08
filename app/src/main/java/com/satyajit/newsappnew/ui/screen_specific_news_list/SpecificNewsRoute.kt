package com.satyajit.newsappnew.ui.screen_specific_news_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.utils.AppConstant

@Composable
fun SpecificNewsRoute(
    newsType: NewsType,
    countryCode: String = "",
    sources: String = "",
    onClickOfNewsITem: (newsUrl: String) -> Unit,
    applicationComponent: ApplicationComponent,
) {
    val viewModel: SpecificNewsViewModel =
        viewModel(factory = applicationComponent.getSpecificNewsViewModelFactory())

    fetchNewsByType(viewModel, newsType, countryCode, sources)

    val onClickOfRetry = { fetchNewsByType(viewModel, newsType, countryCode, sources) }

    val uiState = viewModel.uiState.collectAsState().value

    SpecificNewsScreen(uiState, onClickOfNewsITem, onClickOfRetry)

}

fun fetchNewsByType(
    viewModel: SpecificNewsViewModel,
    newsType: NewsType,
    countryCode: String,
    sources: String
) {
    when (newsType) {
        is NewsType.NewsBySource -> {
            viewModel.fetchNewsBySources(sources)
        }

        is NewsType.NewsByCountry -> {
            viewModel.fetchNewsByCountry(countryCode)
        }
    }

}

sealed class NewsType(var newsType: String) {
    object NewsBySource : NewsType("Source")
    object NewsByCountry : NewsType("Country")
}

