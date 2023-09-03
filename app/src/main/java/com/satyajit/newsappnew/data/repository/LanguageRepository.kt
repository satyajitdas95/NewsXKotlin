package com.satyajit.newsappnew.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.satyajit.newsappnew.data.api.NetworkService
import com.satyajit.newsappnew.data.model.LanguageModel
import com.satyajit.newsappnew.data.model.Sources
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageRepository @Inject constructor(private val networkService: NetworkService) {

    fun getAllLanguages(): Flow<List<LanguageModel>> {
//        return flow {
////            emit(networkService.getAllSources())
//        }.map {
////            it.sources
//        }

        return flow { emptyList<LanguageModel>() }
    }

}