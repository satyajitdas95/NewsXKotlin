package com.satyajit.newsappnew.ui.generic

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBarWithBack(title: String, onBackPressed: () -> Unit) {
    androidx.compose.material.TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed.invoke() }) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        backgroundColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        elevation = 10.dp
    )
}