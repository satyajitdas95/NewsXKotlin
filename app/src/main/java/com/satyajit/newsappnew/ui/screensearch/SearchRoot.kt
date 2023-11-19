package com.satyajit.newsappnew.ui.screensearch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.utils.AppConstant

@Composable
fun SearchRoot(
    applicationComponent: ApplicationComponent,
    onClickOfNewsITem: (newsUrl: String) -> Unit,
    onNavigateBack: () -> Unit,

    ) {

    val viewModel: SearchViewModel =
        viewModel(factory = applicationComponent.getSearchViewModelFactory())

    val onClickOfRetry = { viewModel.fetchNews(AppConstant.COUNTRY) }

    val onSearchDisplayChanged: (String) -> Unit = {viewModel.fetchNews(AppConstant.COUNTRY) }

    val uiState = viewModel.uiState.collectAsState().value

    SearchScreen(viewModel)

}