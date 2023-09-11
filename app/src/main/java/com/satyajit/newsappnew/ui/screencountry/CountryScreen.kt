package com.satyajit.newsappnew.ui.screencountry

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.satyajit.newsappnew.R
import com.satyajit.newsappnew.data.model.Country
import com.satyajit.newsappnew.ui.base.UiState
import com.satyajit.newsappnew.ui.generic.ShowErrorMessageWithRetry
import com.satyajit.newsappnew.ui.generic.ShowLoading


@Composable
fun CountryScreen(
    uiStateCountry: UiState<List<Country>>,
    onClickOfCountry: (countryCode: String) -> Unit,
    onClickOfRetry: () -> Unit
) {
    Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {

        when (uiStateCountry) {
            is UiState.Loading -> {
                ShowLoading()
            }

            is UiState.Error -> {
                ShowErrorMessageWithRetry(
                    message = stringResource(id = R.string.label_error_retry),
                    onClickOfRetry = onClickOfRetry
                )
            }

            is UiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(vertical = 14.dp, horizontal = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(
                        items = uiStateCountry.data,
                        key = { country -> country.countryCode }) { country ->
                        Country(country, onClickOfCountry)
                    }
                }
            }
        }
    }
}


@Composable
fun Country(country: Country, onClickOfCountry: (countryCode: String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClickOfCountry(country.countryCode) },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(country.countryFlag)
                .crossfade(true).build(),
            contentDescription = country.countryName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(75.dp)
                .height(45.dp)
                .padding(vertical = 6.dp).clip(MaterialTheme.shapes.extraSmall)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = country.countryName,
            style = MaterialTheme.typography.bodyLarge,
            color= MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center
        )

    }
}

@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewCountry() {
    Country(Country("India", "en", "in"), {})
}





