package com.satyajit.newsappnew.ui.screentopheadline

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.satyajit.newsappnew.R
import com.satyajit.newsappnew.data.model.Article
import com.satyajit.newsappnew.data.model.Sources
import com.satyajit.newsappnew.ui.base.UiState
import com.satyajit.newsappnew.ui.generic.ShowErrorMessageWithRetry
import com.satyajit.newsappnew.ui.generic.ShowLoadingGlobe
import com.satyajit.newsappnew.ui.screensearch.SearchViewOnly
import com.satyajit.newsappnew.ui.theme.NewsAppNewTheme
import com.satyajit.newsappnew.ui.theme.slightlyDeemphasizedAlpha

@Composable
fun TopHeadlineScreen(
    uiState: UiState<List<Article>>,
    onClickOfNewsITem: (newsUrl: String) -> Unit,
    onClickOfRetry: () -> Unit,
    onClickOfSearch: () -> Unit,
    scrollState: LazyListState
) {
    Surface(modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {

        Column {
            SearchViewOnly(
                text = stringResource(id = R.string.label_search),
                onSearchClicked = onClickOfSearch,
            )

            when (uiState) {
                is UiState.Loading -> {
                    ShowLoadingGlobe()
                }

                is UiState.Error -> {
                    ShowErrorMessageWithRetry(
                        stringResource(id = R.string.error_fetch_news),
                        onClickOfRetry
                    )
                }

                is UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp), state = scrollState
                    ) {
                        items(uiState.data) { article ->
                            NewsArticle(article = article, onClickOfNewsITem)
                        }
                    }
                }
            }
        }


    }
}

@Preview(showBackground = true, name = "Ui mode Dark", uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, name = "Ui mode Light", uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewArticle() {
    NewsAppNewTheme {
        Surface {
            NewsArticle(
                Article(
                    title = "This is A Test Titel",
                    description = "This is a description title to test if it fits into picture",
                    source = Sources(name = "BBC"),
                    imageUrl = "https://images.pexels.com/photos/1535907/pexels-photo-1535907.jpeg?cs=srgb&dl=pexels-karyme-fran%C3%A7a-1535907.jpg&fm=jpg",
                    url = "www.google.come"
                )
            ) {}
        }
    }
}


@Composable
fun NewsArticle(article: Article, onClickOfNewsITem: (newsUrl: String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClickOfNewsITem(article.url ?: "") }

    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(article.imageUrl)
                .crossfade(true).build(),
            contentDescription = "News Thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        )
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 14.dp)) {
            Text(
                text = article.title ?: "",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = article.description ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(slightlyDeemphasizedAlpha),
                maxLines = 2,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = article.source?.name ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(slightlyDeemphasizedAlpha),
                maxLines = 1
            )
        }
    }

}

