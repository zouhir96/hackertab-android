package com.zrcoding.hackertab.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zrcoding.hackertab.core.mapToDomainLanguages
import com.zrcoding.hackertab.domain.Languages
import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.data.repositories.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository
) : ViewModel() {

    private val savedLanguages: Flow<Resource<List<String>>> =
        settingRepository.getUserSetting()
            .map { Resource.Success(data = it.languages) }

    val uiState: StateFlow<SettingUiState> =
        savedLanguages.map {
            when (it) {
                is Resource.Success -> SettingUiState.Success(it.data?.mapToDomainLanguages())
                is Resource.Loading -> SettingUiState.Loading
                is Resource.Error -> SettingUiState.Error
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SettingUiState.Loading
        )

    fun toggleLanguage(languages: Languages) {
        viewModelScope.launch {
            settingRepository.toggleSubscribedLanguage(languages.name)
        }
    }

    fun clear() {
        viewModelScope.launch {
            settingRepository.clear()
        }
    }

}

sealed class SettingUiState {
    data class Success(val languages: List<Languages>?) : SettingUiState()
    object Error : SettingUiState()
    object Loading : SettingUiState()
}