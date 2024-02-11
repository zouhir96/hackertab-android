package com.zrcoding.hackertab.ui.setting.sources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zrcoding.hackertab.core.ChipData
import com.zrcoding.shared.domain.repositories.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingSourcesScreenViewModel @Inject constructor(
    private val settingRepository: SettingRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(SettingSourcesScreenViewState())
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            val sources = settingRepository.getSources()
            settingRepository.getSavedSourcesIds().collectLatest { ids->
                _viewState.update {
                    SettingSourcesScreenViewState(
                        sources = sources.map {
                            ChipData(
                                id = it.value,
                                name = it.label,
                                image = it.icon,
                                selected = it.value in ids
                            )
                        }
                    )
                }
            }
        }
    }

    fun onChipClicked(chipData: ChipData) {
        viewModelScope.launch {
            if (chipData.selected) {
                settingRepository.removeSource(chipData.id)
            } else settingRepository.saveSource(chipData.id)
        }
    }
}