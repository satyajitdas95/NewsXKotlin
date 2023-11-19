package com.satyajit.newsappnew.ui.screenspalsh

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.satyajit.newsappnew.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashCompleted: () -> Unit) {
    val delayDuration by remember { mutableLongStateOf(100L) }

    LaunchedEffect(Unit) {
        delay(delayDuration)
        onSplashCompleted.invoke()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.fullscreen))
        LottieAnimation(
            composition,
            restartOnPlay = true,
            iterations = 10,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )

        Text(
            text = "NewsX",
            color = Color.White,
            fontSize = 34.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview
@Composable
fun PreviewSplash() {
    val navHostController: NavHostController? = null
    SplashScreen({})

}