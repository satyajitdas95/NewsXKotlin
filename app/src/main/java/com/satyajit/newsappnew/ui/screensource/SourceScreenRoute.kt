package com.satyajit.newsappnew.ui.screensource

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.satyajit.newsappnew.di.component.ApplicationComponent

@Composable
fun SourceScreenRoute(applicationComponent: ApplicationComponent, onClickOfSource: (String) -> Unit) {
    val viewModel: SourcesViewModel =
        viewModel(factory = applicationComponent.getSourcesViewModelFactory())


    val onClickOfRetry = { viewModel.fetchAllSources() }

    val uiState = viewModel.uiState.collectAsState().value

    SourcesScreen(uiState, onClickOfSource = onClickOfSource, onClickOfRetry)
}