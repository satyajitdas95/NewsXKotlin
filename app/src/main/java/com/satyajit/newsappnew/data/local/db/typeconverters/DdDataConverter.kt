package com.satyajit.newsappnew.data.local.db.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.satyajit.newsappnew.data.model.Sources

class DdDataConverter {

    @TypeConverter
    fun fromSourcesToString(value: Sources): String {
        val gson = Gson()
        val type = object : TypeToken<Sources>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toSourcesFromString(value: String): Sources {
        val gson = Gson()
        val type = object : TypeToken<Sources>() {}.type
        return gson.fromJson(value, type)
    }
}