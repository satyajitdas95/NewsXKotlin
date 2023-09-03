package com.satyajit.newsappnew.ui.screenspecificnewslist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.satyajit.newsappnew.di.component.ApplicationComponent

@Composable
fun SpecificNewsRoute(
    newsType: NewsType,
    countryCode: String = "",
    sources: String = "",
    languageCode: String = "",
    onClickOfNewsITem: (newsUrl: String) -> Unit,
    applicationComponent: ApplicationComponent,
) {
    val viewModel: SpecificNewsViewModel =
        viewModel(factory = applicationComponent.getSpecificNewsViewModelFactory())

    fetchNewsByType(viewModel = viewModel, newsType = newsType, countryCode =  countryCode, languageCode = languageCode, sources = sources)

    val onClickOfRetry = { fetchNewsByType(viewModel = viewModel, newsType = newsType, countryCode = countryCode, languageCode = languageCode, sources = sources) }

    val uiState = viewModel.uiState.collectAsState().value

    SpecificNewsScreen(uiState, onClickOfNewsITem, onClickOfRetry)

}

fun fetchNewsByType(
    viewModel: SpecificNewsViewModel,
    newsType: NewsType,
    countryCode: String,
    languageCode: String,
    sources: String
) {
    when (newsType) {
        is NewsType.NewsBySource -> {
            viewModel.fetchNewsBySources(sources)
        }

        is NewsType.NewsByCountry -> {
            viewModel.fetchNewsByCountry(countryCode)
        }

        is NewsType.NewsByLanguage -> {
            viewModel.fetchNewsByLanguage(languageCode)
        }
    }

}

sealed class NewsType(var newsType: String) {
    object NewsBySource : NewsType("Source")
    object NewsByCountry : NewsType("Country")
    object NewsByLanguage : NewsType("Language")
}

