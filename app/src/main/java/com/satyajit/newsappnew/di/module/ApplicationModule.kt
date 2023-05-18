package com.satyajit.newsappnew.di.module

import android.content.Context
import com.satyajit.newsappnew.NewsApp
import com.satyajit.newsappnew.data.api.NetworkService
import com.satyajit.newsappnew.data.repository.TopHeadlineRepository
import com.satyajit.newsappnew.di.ApplicationContext
import com.satyajit.newsappnew.di.BaseUrl
import com.satyajit.newsappnew.ui.base.ViewModelProviderFactory
import com.satyajit.newsappnew.ui.top_head_line.TopHeadLineViewModel
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
    fun provideTopHeadLinesViewModelFactory(topHeadlineRepository: TopHeadlineRepository): ViewModelProviderFactory<TopHeadLineViewModel>{
        return ViewModelProviderFactory(TopHeadLineViewModel::class) {
            TopHeadLineViewModel(topHeadlineRepository)
            }
    }
}