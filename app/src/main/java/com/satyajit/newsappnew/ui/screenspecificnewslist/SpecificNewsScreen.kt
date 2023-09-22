package com.satyajit.newsappnew.ui.screenspecificnewslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.satyajit.newsappnew.R
import com.satyajit.newsappnew.data.local.db.entity.TopHeadlineDb
import com.satyajit.newsappnew.data.model.Article
import com.satyajit.newsappnew.ui.base.UiState
import com.satyajit.newsappnew.ui.generic.ShowErrorMessageForNoData
import com.satyajit.newsappnew.ui.generic.ShowErrorMessageWithRetry
import com.satyajit.newsappnew.ui.generic.ShowLoading
import com.satyajit.newsappnew.ui.generic.TopAppBarWithBack
import com.satyajit.newsappnew.ui.screentopheadline.NewsArticle

@Composable
fun SpecificNewsScreen(
    title: String = "",
    uiState: UiState<List<TopHeadlineDb>>,
    onClickOfNewsITem: (newsUrl: String) -> Unit,
    onClickOfRetry: () -> Unit,
    onBackPressed: () -> Unit
) {
    Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        Column {
            TopAppBarWithBack(title = title, onBackPressed = onBackPressed)

            when (uiState) {
                is UiState.Loading -> {
                    ShowLoading()
                }

                is UiState.Error -> {
                    ShowErrorMessageWithRetry(
                        stringResource(id = R.string.error_fetch_news),
                        onClickOfRetry
                    )
                }

                is UiState.Success -> {
                    if (uiState.data.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier.padding(vertical = 14.dp, horizontal = 14.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            items(uiState.data) { article ->
                                NewsArticle(article = article, onClickOfNewsITem)
                            }
                        }
                    } else {
                        ShowErrorMessageForNoData(
                            R.raw.jelly_fish,
                            stringResource(id = R.string.error_no_article_for_this)
                        )
                    }

                }
            }
        }

    }
}

@Preview
@Composable
fun PreviewSpecificNewsList() {
    SpecificNewsScreen(
        "Hello", UiState.Success(emptyList()), {}, {}, {})
}