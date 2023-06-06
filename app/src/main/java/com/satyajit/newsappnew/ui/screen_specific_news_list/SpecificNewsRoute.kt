package com.satyajit.newsappnew.ui.screen_specific_news_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.utils.AppConstant

@Composable
fun SpecificNewsRoute(
    countryCode: String,
    sources: String,
    onClickOfNewsITem: (newsUrl: String) -> Unit,
    applicationComponent: ApplicationComponent,
) {
    val viewModel: SpecificNewsViewModel =
        viewModel(factory = applicationComponent.getSpecificNewsViewModelFactory())

    viewModel.fetchNews(countryCode)

    val onClickOfRetry = {viewModel.fetchNews(AppConstant.COUNTRY)}

    val uiState = viewModel.uiState.collectAsState().value

    SpecificNewsScreen(uiState,onClickOfNewsITem,onClickOfRetry)

}


