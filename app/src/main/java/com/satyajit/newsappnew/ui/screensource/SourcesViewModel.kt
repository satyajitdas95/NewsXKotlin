package com.satyajit.newsappnew.ui.screensource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyajit.newsappnew.data.model.Sources
import com.satyajit.newsappnew.data.repository.SourcesRepository
import com.satyajit.newsappnew.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SourcesViewModel(private val sourcesRepository: SourcesRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Sources>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Sources>>> = _uiState.asStateFlow()

    init {
        fetchAllSources()
    }

    fun fetchAllSources() {
        viewModelScope.launch(Dispatchers.IO) {
            sourcesRepository.getAllSources()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}