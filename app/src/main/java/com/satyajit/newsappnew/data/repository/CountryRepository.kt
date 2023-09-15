package com.satyajit.newsappnew.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.satyajit.newsappnew.data.api.NetworkService
import com.satyajit.newsappnew.data.local.jsonparser.JsonProvider
import com.satyajit.newsappnew.data.model.Country
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepository @Inject constructor(private val networkService: NetworkService,private val jsonProvider: JsonProvider) {

    fun getAllCountries(): Flow<List<Country>> {
        return flow {
            emit(getAllCountriesFromJson())
        }
    }

     private suspend fun getAllCountriesFromJson(): List<Country> {
        val gson = Gson()
        val myType = object : TypeToken<List<Country>>() {}.type
        return gson.fromJson(jsonProvider.getCountryJSON(), myType)
    }

}