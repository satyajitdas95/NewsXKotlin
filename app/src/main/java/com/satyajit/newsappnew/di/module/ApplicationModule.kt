package com.satyajit.newsappnew.di.module

import android.content.Context
import com.satyajit.newsappnew.NewsApp
import com.satyajit.newsappnew.data.api.NetworkService
import com.satyajit.newsappnew.data.repository.NewsRepository
import com.satyajit.newsappnew.data.repository.SourcesRepository
import com.satyajit.newsappnew.di.ApplicationContext
import com.satyajit.newsappnew.di.BaseUrl
import com.satyajit.newsappnew.ui.base.ViewModelProviderFactory
import com.satyajit.newsappnew.ui.screen_source.SourcesViewModel
import com.satyajit.newsappnew.ui.screen_specific_news_list.SpecificNewsViewModel
import com.satyajit.newsappnew.ui.screen_top_head_line.TopHeadLineViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NewsApp) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    fun provideTopHeadLinesViewModelFactory(newsRepository: NewsRepository): ViewModelProviderFactory<TopHeadLineViewModel>{
        return ViewModelProviderFactory(TopHeadLineViewModel::class) {
            TopHeadLineViewModel(newsRepository)
            }
    }

    @Provides
    fun provideSpecificViewModelFactory(newsRepository: NewsRepository): ViewModelProviderFactory<SpecificNewsViewModel>{
        return ViewModelProviderFactory(SpecificNewsViewModel::class) {
            SpecificNewsViewModel(newsRepository)
        }
    }

    @Provides
    fun provideSourcesViewModelFactory(sourcesRepository: SourcesRepository): ViewModelProviderFactory<SourcesViewModel>{
        return ViewModelProviderFactory(SourcesViewModel::class) {
            SourcesViewModel(sourcesRepository)
        }
    }
}