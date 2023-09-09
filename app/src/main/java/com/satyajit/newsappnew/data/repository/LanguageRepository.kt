package com.satyajit.newsappnew.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.satyajit.newsappnew.data.api.NetworkService
import com.satyajit.newsappnew.data.model.Country
import com.satyajit.newsappnew.data.model.LanguageModel
import com.satyajit.newsappnew.data.model.Sources
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageRepository @Inject constructor(private val networkService: NetworkService) {


    fun getAllLanguages(languageJSON: String): Flow<List<LanguageModel>> {
        return flow {
            emit(getAllLanguagesFromJson(languageJSON))
        }
    }

    private suspend fun getAllLanguagesFromJson(languageJSON: String): List<LanguageModel> {
        val gson = Gson()
        val myType = object : TypeToken<List<LanguageModel>>() {}.type
        return gson.fromJson(languageJSON, myType)
    }

}