package com.satyajit.newsappnew.ui.screencountry

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.satyajit.newsappnew.di.component.ApplicationComponent

@Composable
fun CountryRoute(
    applicationComponent: ApplicationComponent,
    onClickOfCountry: (countryCode: String) -> Unit
) {

    val viewModel: CountryViewModel =
        viewModel(factory = applicationComponent.getCountriesViewModelFactory())

}