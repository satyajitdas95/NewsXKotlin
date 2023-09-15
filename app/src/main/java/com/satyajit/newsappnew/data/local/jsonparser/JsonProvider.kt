package com.satyajit.newsappnew.data.local.jsonparser

import android.content.Context
import com.satyajit.newsappnew.R
import javax.inject.Singleton

@Singleton
class JsonProvider(private val applicationContext: Context) {

    fun getLanguageJSON(): String {
        return applicationContext.resources.openRawResource(R.raw.languages)
            .bufferedReader().use { it.readText() }
    }

    fun getCountryJSON(): String {
        return applicationContext.resources.openRawResource(R.raw.countries)
            .bufferedReader().use { it.readText() }
    }
}