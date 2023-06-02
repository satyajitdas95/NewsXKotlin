package com.satyajit.newsappnew.ui.news_details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun TopHeadLineScreen(urlToShow: String?,navHostController: NavHostController) {
    Surface(Modifier.background(MaterialTheme.colors.background)) {
        val webViewState = rememberWebViewState(urlToShow ?: "")
        WebView(
            state = webViewState,
            onCreated = { it.settings.javaScriptEnabled = true },
            captureBackPresses = false
        )
    }
}