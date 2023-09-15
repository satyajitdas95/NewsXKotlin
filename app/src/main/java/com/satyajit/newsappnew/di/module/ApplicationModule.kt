package com.satyajit.newsappnew.di.module

import android.content.Context
import com.satyajit.newsappnew.NewsApp
import com.satyajit.newsappnew.data.api.NetworkService
import com.satyajit.newsappnew.data.local.jsonparser.JsonProvider
import com.satyajit.newsappnew.data.repository.CountryRepository
import com.satyajit.newsappnew.data.repository.LanguageRepository
import com.satyajit.newsappnew.data.repository.NewsRepository
import com.satyajit.newsappnew.data.repository.SearchRepository
import com.satyajit.newsappnew.data.repository.SourcesRepository
import com.satyajit.newsappnew.di.ApplicationContext
import com.satyajit.newsappnew.di.BaseUrl
import com.satyajit.newsappnew.ui.base.ViewModelProviderFactory
import com.satyajit.newsappnew.ui.screencountry.CountryViewModel
import com.satyajit.newsappnew.ui.screenlanguage.LanguageViewModel
import com.satyajit.newsappnew.ui.screensearch.SearchViewModel
import com.satyajit.newsappnew.ui.screensource.SourcesViewModel
import com.satyajit.newsappnew.ui.screenspecificnewslist.SpecificNewsViewModel
import com.satyajit.newsappnew.ui.screentopheadline.TopHeadLineViewModel
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(NetworkService::class.java)
    }

    @Singleton
    @Provides
    fun provideJsonReader(): JsonProvider = JsonProvider(this.application)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideTopHeadLinesViewModelFactory(newsRepository: NewsRepository): ViewModelProviderFactory<TopHeadLineViewModel> {
        return ViewModelProviderFactory(TopHeadLineViewModel::class) {
            TopHeadLineViewModel(newsRepository)
        }
    }

    @Provides
    fun provideSpecificViewModelFactory(newsRepository: NewsRepository): ViewModelProviderFactory<SpecificNewsViewModel> {
        return ViewModelProviderFactory(SpecificNewsViewModel::class) {
            SpecificNewsViewModel(newsRepository)
        }
    }

    @Provides
    fun provideSourcesViewModelFactory(sourcesRepository: SourcesRepository): ViewModelProviderFactory<SourcesViewModel> {
        return ViewModelProviderFactory(SourcesViewModel::class) {
            SourcesViewModel(sourcesRepository)
        }
    }

    @Provides
    fun provideSearchViewModelFactory(searchRepository: SearchRepository): ViewModelProviderFactory<SearchViewModel> {
        return ViewModelProviderFactory(SearchViewModel::class) {
            SearchViewModel(searchRepository)
        }
    }

    @Provides
    fun provideCountryViewModelFactory(countryRepository: CountryRepository): ViewModelProviderFactory<CountryViewModel> {
        return ViewModelProviderFactory(CountryViewModel::class) {
            CountryViewModel(countryRepository)
        }
    }

    @Provides
    fun provideLanguageViewModelFactory(languageRepository: LanguageRepository): ViewModelProviderFactory<LanguageViewModel> {
        return ViewModelProviderFactory(LanguageViewModel::class) {
            LanguageViewModel(languageRepository)
        }
    }
}