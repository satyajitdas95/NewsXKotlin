package com.satyajit.newsappnew.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.satyajit.newsappnew.data.api.NetworkService
import com.satyajit.newsappnew.data.local.db.NewsAppDb
import com.satyajit.newsappnew.data.local.db.entity.TopHeadlineDb
import com.satyajit.newsappnew.data.local.db.typeconverters.NetworkResponseToDbEntityConverter
import com.satyajit.newsappnew.data.model.Article
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: NewsAppDb
) {

    fun getTopHeadlines(country: String): Flow<List<TopHeadlineDb>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.flatMapConcat { topHeadlinesResponse ->
            return@flatMapConcat flow {
                emit(
                    databaseService.topHeadlineDAO().deleteAllTopHeadLines()
                )
            }.flatMapConcat {
                val listOfTopHeadLine = topHeadlinesResponse.articles.map { article ->
                    NetworkResponseToDbEntityConverter.convertTopHeadLineNetworkToTopHeadLineDb(
                        article
                    )
                }
                return@flatMapConcat flow {
                    emit(
                        databaseService.topHeadlineDAO().insertAllTopHeadLine(listOfTopHeadLine)
                    )
                }
            }
        }.flatMapConcat {
            return@flatMapConcat flowOf(
                databaseService.topHeadlineDAO().getAllTopHeadLines()
            )
        }
    }

    fun getTopHeadlinesBySources(sources: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlinesBySources(sources))
        }.map {
            it.articles.filter { article -> article.title != null && article.title != "[Removed]" }
        }
    }

}