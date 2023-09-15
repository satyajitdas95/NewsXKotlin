package com.satyajit.newsappnew.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.satyajit.newsappnew.data.api.NetworkService
import com.satyajit.newsappnew.data.local.jsonparser.JsonProvider
import com.satyajit.newsappnew.data.model.LanguageModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageRepository @Inject constructor(private val networkService: NetworkService,private val jsonProvider: JsonProvider) {


    fun getAllLanguages(): Flow<List<LanguageModel>> {
        return flow {
            emit(getAllLanguagesFromJson())
        }
    }

    private fun getAllLanguagesFromJson(): List<LanguageModel> {
        val gson = Gson()
        val myType = object : TypeToken<List<LanguageModel>>() {}.type
        return gson.fromJson(jsonProvider.getLanguageJSON(), myType)
    }

}