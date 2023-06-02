package com.satyajit.newsappnew.ui.country

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satyajit.newsappnew.R
import com.satyajit.newsappnew.data.model.Country


@Composable
fun CountryScreen(countryList: List<Country>, onClickOfCountry: (countryCode: String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(countryList) { photo ->
            Country(photo, onClickOfCountry)
        }
    }

}


@Composable
fun Country(country: Country, onClickOfCountry: (countryCode: String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colorScheme.onSurface)
            .clickable { onClickOfCountry(country.countryCode) }
    ) {
        Text(
            text = country.countryName,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Icon(
            painter = painterResource(id = country.iconID),
            contentDescription = country.countryName
        )
    }
}

@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewCountry() {
    Country(Country(R.drawable.`in`,"India","in"),{})
}





