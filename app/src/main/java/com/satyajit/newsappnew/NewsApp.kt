package com.satyajit.newsappnew

import android.app.Application
import com.satyajit.newsappnew.di.component.ApplicationComponent
import com.satyajit.newsappnew.di.module.ApplicationModule
import com.satyajit.newsappnew.di.component.DaggerApplicationComponent

class NewsApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependecies()
    }

    private fun injectDependecies(){
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}