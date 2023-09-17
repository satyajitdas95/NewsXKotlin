package com.satyajit.newsappnew.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.satyajit.newsappnew.data.model.Sources

@Entity(tableName = "topNews")
data class TopHeadlineDb(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String?,
    val description: String?,
    val url: String?,
    val imgUrl: String?,
    val source: Sources?
)
