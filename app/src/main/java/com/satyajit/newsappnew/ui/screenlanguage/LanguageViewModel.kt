package com.satyajit.newsappnew.ui.screenlanguage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyajit.newsappnew.data.model.LanguageModel
import com.satyajit.newsappnew.data.repository.LanguageRepository
import com.satyajit.newsappnew.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class LanguageViewModel @Inject constructor(private val languageRepository: LanguageRepository) :
    ViewModel() {

    private val _uiStateFlow = MutableStateFlow<UiState<List<LanguageModel>>>(UiState.Loading)

    val uiStateFlow: StateFlow<UiState<List<LanguageModel>>> = _uiStateFlow.asStateFlow()

    init {
        fetchLanguage()
    }

    private fun fetchLanguage() {
        viewModelScope.launch(Dispatchers.IO) {
            languageRepository.getAllLanguages().catch {
                _uiStateFlow.value = UiState.Error(it.toString())
            }.collect {
                _uiStateFlow.value = UiState.Success(it)
            }
        }
    }

}