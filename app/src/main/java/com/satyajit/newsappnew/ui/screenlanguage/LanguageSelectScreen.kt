@file:OptIn(ExperimentalFoundationApi::class)

package com.satyajit.newsappnew.ui.screenlanguage

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satyajit.newsappnew.data.model.LanguageModel
import com.satyajit.newsappnew.ui.base.UiState
import com.satyajit.newsappnew.ui.generic.ShowLoading
import kotlin.random.Random


@Composable
fun LanguageScreen(
    uiStateLanguage: UiState<List<LanguageModel>>,
    onClickOfLanguage: (languageCode: String) -> Unit
) {
    Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {

        when (uiStateLanguage) {

            is UiState.Loading -> {
                ShowLoading()
            }

            is UiState.Error -> {

            }

            is UiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(vertical = 14.dp, horizontal = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(
                        items = uiStateLanguage.data,
                        key = { language -> language.languageCode }) { language ->
                        Column(Modifier.animateItemPlacement(tween(3000))) {
                            LanguageItem(language, onClickOfLanguage)
                        }
                    }
                }
            }

        }

    }
}


@Composable
fun LanguageItem(language: LanguageModel, onClickOfLanguage: (languageCode: String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClickOfLanguage(language.languageCode) },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = language.languageTextOriginal,
            style = MaterialTheme.typography.titleLarge,
            color = Color(
                red = Random.nextInt(40, 256),
                green = Random.nextInt(40, 256),
                blue = Random.nextInt(40, 256)
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = language.languageName,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center
        )

    }
}

@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewCountry() {
    LanguageItem(LanguageModel("English", "English", "in"), {})
}





