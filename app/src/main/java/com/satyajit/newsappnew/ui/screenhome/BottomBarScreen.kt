package com.satyajit.newsappnew.ui.screenhome

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object TopNews : BottomBarScreen(
        route = "topnews",
        title = "Top News",
        icon = Icons.Default.Home
    )

    object Sources : BottomBarScreen(
        route = "sources",
        title = "Sources",
        icon = Icons.Default.CheckCircle
    )

    object Country : BottomBarScreen(
        route = "country",
        title = "Country",
        icon = Icons.Default.Star
    )

    object Language : BottomBarScreen(
        route = "language",
        title = "Language",
        icon = Icons.Default.Info
    )
}
