package com.satyajit.newsappnew.ui.screenhome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.navigation.HomeScreenNavGraph

@Composable
fun HomeScreen(
    applicationComponent: ApplicationComponent,
    onNavigateSearch: () -> Unit,
    navHostController: NavHostController = rememberNavController()
) {

    val scrollState = rememberLazyListState()

    val shouldHideBottomBar by remember(scrollState) {
        derivedStateOf {
            scrollState.firstVisibleItemScrollOffset == 0
        }
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = shouldHideBottomBar,
                enter = slideInVertically(animationSpec = tween(durationMillis = 800)),
                exit = slideOutVertically(animationSpec = tween(durationMillis = 800)),
            ) {
                BottomBar(navHostController = navHostController)
            }

        })
    { innerPadding ->

        Surface(modifier = Modifier.padding(innerPadding)) {
            HomeScreenNavGraph(
                navController = navHostController,
                applicationComponent = applicationComponent, onNavigateSearch,
                scrollState = scrollState
            )
        }

    }
}




