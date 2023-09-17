package com.satyajit.newsappnew.data.local.db.typeconverters

import com.satyajit.newsappnew.data.local.db.entity.TopHeadlineDb
import com.satyajit.newsappnew.data.model.Article


object NetworkResponseToDbEntityConverter {

    fun convertTopHeadLineNetworkToTopHeadLineDb(topHeadLineNetwork: Article): TopHeadlineDb {
        return TopHeadlineDb(
            id = 0,
            title = topHeadLineNetwork.title,
            description = topHeadLineNetwork.description,
            url = topHeadLineNetwork.url,
            imgUrl = topHeadLineNetwork.imageUrl,
            source = topHeadLineNetwork.source,
        )
    }
}