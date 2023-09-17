package com.satyajit.newsappnew.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.satyajit.newsappnew.data.local.db.dao.TopHeadLineDAO
import com.satyajit.newsappnew.data.local.db.entity.TopHeadlineDb
import com.satyajit.newsappnew.data.local.db.typeconverters.DdDataConverter

@Database(entities = [TopHeadlineDb::class], version = 1)
@TypeConverters(DdDataConverter::class)
abstract class NewsAppDb : RoomDatabase() {

    abstract fun topHeadlineDAO(): TopHeadLineDAO

}