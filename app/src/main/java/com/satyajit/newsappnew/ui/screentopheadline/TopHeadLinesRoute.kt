package com.satyajit.newsappnew.ui.screentopheadline

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.utils.AppConstant

@Composable
fun TopHeadLinesRoute(
    onClickOfNewsITem: (newsUrl: String) -> Unit,
    onClickOfSearch: () -> Unit,
    applicationComponent: ApplicationComponent,
    scrollState: LazyListState,
) {
    val viewModel: TopHeadLineViewModel =
        viewModel(factory = applicationComponent.getTopHeadlineViewModelFactory())

    val onClickOfRetry = { viewModel.fetchNews(AppConstant.COUNTRY) }

    val uiState = viewModel.uiState.collectAsState().value

    TopHeadlineScreen(
        uiState = uiState,
        onClickOfNewsITem = onClickOfNewsITem,
        onClickOfRetry = onClickOfRetry,
        onClickOfSearch = onClickOfSearch,
        scrollState = scrollState
    )

}


