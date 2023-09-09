package com.satyajit.newsappnew.ui.screencountry

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyajit.newsappnew.data.model.Country
import com.satyajit.newsappnew.data.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.satyajit.newsappnew.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch

class CountryViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Country>>> = _uiState.asStateFlow()

    fun fetchAllCountries(countryJson: String) {
        viewModelScope.launch(Dispatchers.IO) {
            countryRepository.getAllCountries(countryJson)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}
