@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.satyajit.newsappnew.ui.screensearch

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.satyajit.newsappnew.R
import com.satyajit.newsappnew.ui.custom.coloredShadow


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(viewModel: SearchViewModel) {

    val searchTextState by viewModel.searchTextState

    Scaffold(topBar = {
        MainAppBar(searchTextState = searchTextState, onTextChange = {
            viewModel.updateSearchTextState(newValue = it)
        }, onSearchClicked = {
            Log.d("Searched Text", it)
        })
    }) {}
}

@Composable
fun MainAppBar(
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    SearchAppBar(
        text = searchTextState,
        onTextChange = onTextChange,
        onSearchClicked = onSearchClicked,
    )
}


@ExperimentalMaterial3Api
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        tonalElevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colorScheme.primary
    ) {
        val focusRequester = remember { FocusRequester() }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Search here...",
                    color = Color.Black
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize, color = Color.Black
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier.alpha(ContentAlpha.medium), onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                }

            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    IconButton(onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon",
                            tint = Color.Black
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done, keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onSearch = {
                focusRequester.freeFocus()
                onSearchClicked(text)
            }),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                cursorColor = Color.Black.copy(alpha = ContentAlpha.medium)
            )
        )

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}

@Composable
fun SearchViewOnly(
    text: String,
    onSearchClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(56.dp)
            .coloredShadow(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.medium
                )
                .clip(MaterialTheme.shapes.medium)
                .clickable { onSearchClicked }
                .clickable(enabled = true, onClick = onSearchClicked)

        ) {

            Image(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search Icon",
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp)
                    .align(CenterVertically)
                    .padding(start = 16.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            )

            Text(
                text = stringResource(id = R.string.label_search),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(CenterVertically),
                color = MaterialTheme.colorScheme.onPrimary
            )

        }

    }
}

//@Preview(name = "LightMode", uiMode = UI_MODE_NIGHT_NO)
//@Preview(name = "DarkMode", uiMode = UI_MODE_NIGHT_YES)
//@Composable
//fun PreviewSearchBar() {
//    SearchAppBar("Hello", {}, {})
//}


@Preview(name = "LightMode", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "DarkMode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewSearchViewOnly() {
    SearchViewOnly("Hello", {})
}




