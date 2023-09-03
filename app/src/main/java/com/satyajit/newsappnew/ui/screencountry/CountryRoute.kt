package com.satyajit.newsappnew.ui.screencountry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.satyajit.newsappnew.di.component.ApplicationComponent

@Composable
fun CountryRoute(
    applicationComponent: ApplicationComponent, onClickOfCountry: (countryCode: String) -> Unit
) {

    val viewModel: CountryViewModel =
        viewModel(factory = applicationComponent.getCountriesViewModelFactory())

    val uiStateCountry = viewModel.uiState.collectAsState().value

    val onClickOfRetry: () -> Unit = { viewModel.fetchAllCountries() }

    CountryScreen(
        uiStateCountry = uiStateCountry,
        onClickOfCountry = onClickOfCountry,
        onClickOfRetry = onClickOfRetry
    )

}