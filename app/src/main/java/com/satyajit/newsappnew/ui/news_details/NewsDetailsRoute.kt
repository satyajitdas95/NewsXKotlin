@file:OptIn(ExperimentalMaterial3Api::class)

package com.satyajit.newsappnew.ui.news_details

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.satyajit.newsappnew.R

@Composable
fun NewsDetailsScreen(newsUrl: String) {
    var newUrl = newsUrl
    if (!newUrl.startsWith("http://") && !newUrl.startsWith("https://")) {
        newUrl = Uri.parse("http://$newUrl").toString()
    }

    val context = LocalContext.current
    var mCustomTabsServiceConnection: CustomTabsServiceConnection? = null
    var mClient: CustomTabsClient? = null
    var mCustomTabsSession: CustomTabsSession? = null
    mCustomTabsServiceConnection = object : CustomTabsServiceConnection() {
        override fun onCustomTabsServiceConnected(
            componentName: ComponentName,
            customTabsClient: CustomTabsClient
        ) {
            //Pre-warming
            mClient = customTabsClient
            mClient?.warmup(0L)
            mCustomTabsSession = mClient?.newSession(null)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mClient = null
        }
    }
    CustomTabsClient.bindCustomTabsService(
        context,
        "com.android.chrome",
        mCustomTabsServiceConnection
    )
    val customTabsIntent = CustomTabsIntent.Builder(mCustomTabsSession)
        //.setToolbarColor(color)
        .setShowTitle(true)
        .build()

    customTabsIntent.launchUrl(context, Uri.parse(newUrl))

}


//@Composable
//fun customChromeTab(context: Context,newsUrl:String) {
//    // on below line we are creating a
//    // variable for getting context.
//    val ctx = LocalContext.current
//
//    // on below line we are creating a column
//    Column(
//        // inside this column we are specifying modifier
//        // to column as max size, max height and ,max width.
//        modifier = Modifier
//            .fillMaxSize()
//            .fillMaxWidth()
//            .fillMaxHeight(),
//
//        // on below line we are adding vertical
//        // arrangement and horizontal alignment.
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        // on the below line we are creating a button.
//        Button(
//
//            // on below line we are adding modifier to our button
//            // for adding max width and adding padding to button
//            // from all sides.
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(18.dp),
//
//            // on below line we are adding color for our button.
//
//            // on below line we are adding on click for our button.
//            onClick = {
//
//                // on below line we are creating an open tab method
//                // to open our custom chrome tabs.
//                openTab(context,newsUrl)
//
//            })
//        // on the below line we are creating
//        // a text for our button
//        // and adding padding to our text.
//        {
//            Text(text = "Open custom chrome tab", modifier = Modifier.padding(8.dp))
//
//        }
//
//    }
//}
//
//fun openTab(context: Context, url: String) {
//    // on below line we are creating a variable for
//    // package name and specifying package name as
//    // package of chrome application.
//    val package_name = "com.android.chrome"
//
//    // on below line we are creating a variable for
//    // our URL which we have to open in chrome tabs
//    val URL = url
//
//    // on below line we are creating a variable
//    // for the activity and initializing it.
//    val activity = (context as? Activity)
//
//    // on below line we are creating a variable for
//    // our builder and initializing it with
//    // custom tabs intent
//    val builder = CustomTabsIntent.Builder()
//
//    // on below line we are setting show title
//    // to true to display the title for
//    // our chrome tabs.
//    builder.setShowTitle(true)
//
//    // on below line we are enabling instant
//    // app to open if it is available.
//    builder.setInstantAppsEnabled(true)
//
//    // on below line we are setting tool bar color for our custom chrome tabs.
//    builder.setToolbarColor(ContextCompat.getColor(context, R.color.purple_200))
//
//    // on below line we are creating a
//    // variable to build our builder.
//    val customBuilder = builder.build()
//
//    // on below line we are checking if the package name is null or not.
//    if (package_name != null) {
//        // on below line if package name is not null
//        // we are setting package name for our intent.
//        customBuilder.intent.setPackage(package_name)
//
//        // on below line we are calling launch url method
//        // and passing url to it on below line.
//        customBuilder.launchUrl(context, Uri.parse(URL))
//    } else {
//        // this method will be called if the
//        // chrome is not present in user device.
//        // in this case we are simply passing URL
//        // within intent to open it.
//        val i = Intent(Intent.ACTION_VIEW, Uri.parse(URL))
//
//        // on below line we are calling start
//        // activity to start the activity.
//        activity?.startActivity(i)
//    }
//
//}