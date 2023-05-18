package com.satyajit.newsappnew.ui.top_head_line

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.satyajit.newsappnew.data.model.Article
import com.satyajit.newsappnew.data.model.Source
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.ui.base.UiState
import com.satyajit.newsappnew.ui.theme.NewsAppNewTheme

@Composable
fun NewsArticle(article: Article) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "News Thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.tertiary)
            )
            Column(modifier = Modifier.padding(horizontal = 6.dp, vertical = 6.dp)) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = article.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }

}

@Composable
fun TopHeadlineScreen(
    applicationComponent: ApplicationComponent,
    navHostController: NavHostController
) {

    val viewModel : TopHeadLineViewModel = viewModel(factory = applicationComponent.getTopHeadlineViewModelFactory())

    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        is UiState.Loading -> {

        }

        is UiState.Error -> {

        }

        is UiState.Success -> {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.data) { article ->
                    NewsArticle(article = article)
                }
            }
        }


    }



}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsAppNewTheme {
        Surface {
            NewsArticle(
                Article(
                    title = "This is A Test Titel",
                    description = "This is a description title to test if it fits into picture",
                    source = Source("1", "BBC"),
                    imageUrl = "https://images.pexels.com/photos/1535907/pexels-photo-1535907.jpeg?cs=srgb&dl=pexels-karyme-fran%C3%A7a-1535907.jpg&fm=jpg",
                    url = "www.google.come"
                )
            )
        }
    }
}
