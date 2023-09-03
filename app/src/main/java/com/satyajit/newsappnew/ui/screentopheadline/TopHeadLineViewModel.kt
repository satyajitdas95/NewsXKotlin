package com.satyajit.newsappnew.ui.screentopheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyajit.newsappnew.data.model.Article
import com.satyajit.newsappnew.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.satyajit.newsappnew.ui.base.UiState
import com.satyajit.newsappnew.utils.AppConstant
import kotlinx.coroutines.flow.catch

class TopHeadLineViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState.asStateFlow()

    init {
        fetchNews(AppConstant.COUNTRY)
    }

    fun fetchNews(countryCode:String) {
        viewModelScope.launch {
            newsRepository.getTopHeadlines(countryCode)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}
