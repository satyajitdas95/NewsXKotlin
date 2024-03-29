package com.satyajit.newsappnew.ui.screentopheadline

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
import com.satyajit.newsappnew.utils.AppConstant
import com.satyajit.newsappnew.utils.network.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch

class TopHeadLineViewModel(
    private val networkHelper: NetworkHelper,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<TopHeadlineDb>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<TopHeadlineDb>>> = _uiState.asStateFlow()

    init {
        fetchNews(AppConstant.COUNTRY)
    }

    fun fetchNews(countryCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (networkHelper.getIfConnected()) {
                newsRepository.getTopHeadlines(countryCode)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    }
            }
        }
    }

}
