package com.satyajit.newsappnew.di.component

import android.content.Context
import com.satyajit.newsappnew.NewsApp
import com.satyajit.newsappnew.data.api.NetworkService
import com.satyajit.newsappnew.di.ApplicationContext
import com.satyajit.newsappnew.di.module.ApplicationModule
import com.satyajit.newsappnew.ui.base.ViewModelProviderFactory
import com.satyajit.newsappnew.ui.screencountry.CountryViewModel
import com.satyajit.newsappnew.ui.screenlanguage.LanguageViewModel
import com.satyajit.newsappnew.ui.screensearch.SearchViewModel
import com.satyajit.newsappnew.ui.screensource.SourcesViewModel
import com.satyajit.newsappnew.ui.screenspecificnewslist.SpecificNewsViewModel
import com.satyajit.newsappnew.ui.screentopheadline.TopHeadLineViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApp)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineViewModelFactory() : ViewModelProviderFactory<TopHeadLineViewModel>
    fun getSpecificNewsViewModelFactory() : ViewModelProviderFactory<SpecificNewsViewModel>
    fun getSourcesViewModelFactory() : ViewModelProviderFactory<SourcesViewModel>
    fun getSearchViewModelFactory() : ViewModelProviderFactory<SearchViewModel>
    fun getCountriesViewModelFactory() : ViewModelProviderFactory<CountryViewModel>
    fun getLanguageViewModelFactory() : ViewModelProviderFactory<LanguageViewModel>


}