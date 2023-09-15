package com.satyajit.newsappnew.ui.screencountry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.satyajit.newsappnew.R
import com.satyajit.newsappnew.di.component.ApplicationComponent

@Composable
fun CountryScreenRoute(
    applicationComponent: ApplicationComponent, onClickOfCountry: (countryCode: String) -> Unit
) {

    val countryViewModel: CountryViewModel =
        viewModel(factory = applicationComponent.getCountriesViewModelFactory())

    val uiStateCountry = countryViewModel.uiState.collectAsState().value

    val onClickOfRetry: () -> Unit = { countryViewModel.fetchAllCountries() }

    CountryScreen(
        uiStateCountry = uiStateCountry,
        onClickOfCountry = onClickOfCountry,
        onClickOfRetry = onClickOfRetry
    )

}