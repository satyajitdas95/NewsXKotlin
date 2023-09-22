package com.satyajit.newsappnew.ui.screenspecificnewslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyajit.newsappnew.data.local.db.entity.TopHeadlineDb
import com.satyajit.newsappnew.data.model.Article
import com.satyajit.newsappnew.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.satyajit.newsappnew.ui.base.UiState
import kotlinx.coroutines.flow.catch

class SpecificNewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<TopHeadlineDb>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<TopHeadlineDb>>> = _uiState.asStateFlow()


    fun fetchNewsByCountry(countryCode:String) {
        viewModelScope.launch {
            newsRepository.getTopHeadlines(countryCode)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    fun fetchNewsBySources(sourceName:String) {
        viewModelScope.launch {
            newsRepository.getTopHeadlinesBySources(sourceName)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    fun fetchNewsByLanguage(languageCode:String) {
        viewModelScope.launch {
            newsRepository.getTopHeadlinesBySources(languageCode)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}
