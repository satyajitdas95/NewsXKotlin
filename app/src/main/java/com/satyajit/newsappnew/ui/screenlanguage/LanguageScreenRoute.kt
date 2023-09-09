package com.satyajit.newsappnew.ui.screenlanguage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.satyajit.newsappnew.R
import com.satyajit.newsappnew.data.model.LanguageModel
import com.satyajit.newsappnew.di.component.ApplicationComponent

@Composable
fun LanguageScreenRoute(
    applicationComponent: ApplicationComponent,
    onClickOfLanguage: (languageCode: String) -> Unit
) {

    val languageViewModel : LanguageViewModel = viewModel(factory = applicationComponent.getLanguageViewModelFactory())

    val context = LocalContext.current
    val languageJson = context.resources.openRawResource(R.raw.languages)
        .bufferedReader().use { it.readText() }

    languageViewModel.fetchAllLanguage(languageJson)

    val uiStateLanguage = languageViewModel.uiStateFlow.collectAsState().value

    LanguageScreen(uiStateLanguage = uiStateLanguage, onClickOfLanguage = onClickOfLanguage)

}