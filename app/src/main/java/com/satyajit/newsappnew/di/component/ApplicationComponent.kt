package com.satyajit.newsappnew.di.component

import android.content.Context
import com.satyajit.newsappnew.NewsApp
import com.satyajit.newsappnew.data.api.NetworkService
import com.satyajit.newsappnew.di.ApplicationContext
import com.satyajit.newsappnew.di.module.ApplicationModule
import com.satyajit.newsappnew.ui.base.ViewModelProviderFactory
import com.satyajit.newsappnew.ui.top_head_line.TopHeadLineViewModel
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

//    fun getTopHeadlineRepository(): TopHeadlineRepository

}