package com.satyajit.newsappnew.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.satyajit.newsappnew.data.local.db.entity.TopHeadlineDb

@Dao
interface TopHeadLineDAO {

    @Query("Select * from topNews")
    fun getAllTopHeadLines(): List<TopHeadlineDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTopHeadLine(articles: List<TopHeadlineDb>)

    @Query("Delete from topnews")
    fun deleteAllTopHeadLines()

}