package com.satyajit.newsappnew.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NetworkHelperImpl(private val context: Context) : NetworkHelper {

    override suspend fun getIfConnected(): Boolean {
        return suspendCoroutine { continuation ->
            checkForConnection { isConnected ->
                continuation.resume(isConnected)
            }
        }
    }

    private fun checkForConnection(callBack: (Boolean) -> Unit) {

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                callBack.invoke(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                callBack.invoke(false)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                callBack.invoke(false)
            }
        }

        val connectivityManager =
            context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)

    }
}