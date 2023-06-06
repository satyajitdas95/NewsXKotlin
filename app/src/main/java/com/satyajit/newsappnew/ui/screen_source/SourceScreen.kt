package com.satyajit.newsappnew.ui.screen_source

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satyajit.newsappnew.data.model.Sources
import com.satyajit.newsappnew.ui.base.UiState
import com.satyajit.newsappnew.ui.generic.showErrorMessageWithRetry
import com.satyajit.newsappnew.ui.generic.showLoading

@Composable
fun SourcesScreen(
    uiState: UiState<List<Sources>>,
    onClickOfSource: (sourceName: String) -> Unit,
    onClickOfRetry: () -> Unit
) {
    Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {

        when (uiState) {
            is UiState.Loading -> {
                showLoading()
            }

            is UiState.Error -> {
                showErrorMessageWithRetry("Something went Wrong", onClickOfRetry)
            }

            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier.padding(vertical = 14.dp, horizontal = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(items = uiState.data, key = { source -> source.id }) { sourceItem ->
                        SourceItem(sourceItem, onClickOfSource)
                    }

                }
            }
        }


    }


}

@Composable
fun SourceItem(sourcesItem: Sources, onClickOfSources: (sourceName: String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.shapes.medium)
            .padding(12.dp)
            .clickable { onClickOfSources(sourcesItem.name) },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = sourcesItem.name,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        sourcesItem.description?.let { description ->
            Text(
                text = description.trim(),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start, maxLines = 2
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            sourcesItem.category?.let { category ->
                Text(
                    text = category,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            sourcesItem.language?.let { language ->
                Text(
                    text = language,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            sourcesItem.country?.let { country ->
                Text(
                    text = country,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }
        }


    }
}


@Preview(name = "Dark Theme", uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Light Theme", uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewSourceItem() {
    SourceItem(
        Sources(
            name = "BBC News", description = "We provide the best new in the world.",
            category = "Business",
            language = "En",
            country = "US"
        )
    ) {}
}