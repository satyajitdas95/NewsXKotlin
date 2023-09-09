package com.satyajit.newsappnew.ui.screenhome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults.containerColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.SnackbarDefaults.contentColor
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.satyajit.newsappnew.ui.custom.coloredShadow

@Composable
fun BottomBar(navHostController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.TopNews,
        BottomBarScreen.Sources,
        BottomBarScreen.Country,
        BottomBarScreen.Language
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigationBar(screens = screens, navHostController = navHostController)
    }
}

@Composable
fun BottomNavigationBar(screens: List<BottomBarScreen>, navHostController: NavHostController) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(
        modifier = Modifier
            .coloredShadow(Color.White, shadowRadius = 16.dp, borderRadius = 6.dp),
        tonalElevation = 40.dp,
        actions = {
            screens.forEachIndexed { index, item ->
                AddItem(
                    screen = item,
                    currentDestination = currentDestination,
                    navController = navHostController
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.primary,
    )
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen, currentDestination: NavDestination?, navController: NavHostController
) {
    BottomNavigationItem(label = {
        Text(text = screen.title)
    },
        icon = {
            Icon(
                imageVector = screen.icon, contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f),
        selectedContentColor =MaterialTheme.colorScheme.onPrimary,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        })
}

@Preview
@Composable
fun PreviewBottomBar() {
    val screens = listOf(
        BottomBarScreen.TopNews,
        BottomBarScreen.Sources,
        BottomBarScreen.Country,
        BottomBarScreen.Language
    )
    BottomNavigationBar(screens, rememberNavController())
}