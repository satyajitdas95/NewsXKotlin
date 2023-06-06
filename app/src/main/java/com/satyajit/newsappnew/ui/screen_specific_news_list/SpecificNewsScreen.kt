package com.satyajit.newsappnew.ui.screen_specific_news_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.satyajit.newsappnew.R
import com.satyajit.newsappnew.data.model.Article
import com.satyajit.newsappnew.ui.base.UiState
import com.satyajit.newsappnew.ui.generic.showErrorMessageWithRetry
import com.satyajit.newsappnew.ui.generic.showLoading
import com.satyajit.newsappnew.ui.screen_top_head_line.NewsArticle

@Composable
fun SpecificNewsScreen(
    uiState: UiState<List<Article>>,
    onClickOfNewsITem: (newsUrl: String) -> Unit,
    onClickOfRetry: () -> Unit
) {
    Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        when (uiState) {
            is UiState.Loading -> {
                showLoading()
            }

            is UiState.Error -> {
                showErrorMessageWithRetry(stringResource(id = R.string.error_fetch_news),onClickOfRetry)
            }

            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(uiState.data) { article ->
                        NewsArticle(article = article, onClickOfNewsITem)
                    }
                }
            }
        }


    }
}