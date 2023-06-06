package com.satyajit.newsappnew.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.satyajit.newsappnew.data.api.NetworkService
import com.satyajit.newsappnew.data.model.Sources
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SourcesRepository @Inject constructor(private val networkService: NetworkService) {

    fun getAllSources(): Flow<List<Sources>> {
        return flow {
            emit(networkService.getAllSources())
        }.map {
            it.sources
        }
    }

}