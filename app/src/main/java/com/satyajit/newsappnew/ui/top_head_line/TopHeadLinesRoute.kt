package com.satyajit.newsappnew.ui.top_head_line

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.satyajit.newsappnew.di.component.ApplicationComponent

@Composable
fun TopHeadLinesRoute(
    onClickOfNewsITem: (newsUrl: String) -> Unit,
    applicationComponent: ApplicationComponent,
) {
    val viewModel: TopHeadLineViewModel =
        viewModel(factory = applicationComponent.getTopHeadlineViewModelFactory())

    val onClickOfRetry = {viewModel.fetchNews()}

    val uiState = viewModel.uiState.collectAsState().value

    TopHeadlineScreen(uiState,onClickOfNewsITem,onClickOfRetry)

}


