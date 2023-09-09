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
        icon = Icons.Filled.Home
    )

    object Sources : BottomBarScreen(
        route = "sources",
        title = "Sources",
        icon = Icons.Filled.CheckCircle
    )

    object Country : BottomBarScreen(
        route = "country",
        title = "Country",
        icon = Icons.Filled.Star
    )

    object Language : BottomBarScreen(
        route = "language",
        title = "Language",
        icon = Icons.Filled.Info
    )
}
