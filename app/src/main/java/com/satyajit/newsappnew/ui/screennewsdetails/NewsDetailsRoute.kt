package com.satyajit.newsappnew.ui.screennewsdetails

import android.content.ComponentName
import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

fun NavigateToNewsDetails(context : Context,newsUrl: String) {
    var newUrl = newsUrl
    if (!newUrl.startsWith("http://") && !newUrl.startsWith("https://")) {
        newUrl = Uri.parse("http://$newUrl").toString()
    }

    var mCustomTabsServiceConnection: CustomTabsServiceConnection?
    var mClient: CustomTabsClient?
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
        .setShowTitle(true)
        .build()

    customTabsIntent.launchUrl(context, Uri.parse(newUrl))

}
