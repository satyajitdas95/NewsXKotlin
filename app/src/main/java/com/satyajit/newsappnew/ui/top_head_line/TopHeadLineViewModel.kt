package com.satyajit.newsappnew.ui.top_head_line

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyajit.newsappnew.data.model.Article
import com.satyajit.newsappnew.data.repository.TopHeadlineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.satyajit.newsappnew.ui.base.UiState
import com.satyajit.newsappnew.utils.AppConstant
import kotlinx.coroutines.flow.catch

class TopHeadLineViewModel(private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState.asStateFlow()

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines(AppConstant.COUNTRY)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

//    private fun fetchNews() {
//        viewModelScope.launch {
//            _uiState.value = UiState.Success(NewsData.listOfArticle)
//        }
//    }

}
