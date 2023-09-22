package com.satyajit.newsappnew.utils.network

interface NetworkHelper {
    suspend fun getIfConnected() :Boolean
}