package com.zrcoding.hackertab.ui.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.core.ChipData
import com.zrcoding.hackertab.core.ChipGroup
import com.zrcoding.hackertab.domain.Languages
import com.zrcoding.hackertab.domain.ListOfLanguages.listOfLanguages

@Composable
fun SettingScreen(
    modifier: Modifier,
    viewModel: SettingViewModel
) {
    val uiState: SettingUiState by viewModel.uiState.collectAsState()
    SettingContent(
        modifier = modifier,
        settingState = uiState,
        toggleLanguage = viewModel::toggleLanguage,
        clearDataStore = viewModel::clear
    )
}

@Composable
fun SettingContent(
    modifier: Modifier,
    settingState: SettingUiState,
    toggleLanguage: (Languages) -> Unit,
    clearDataStore: () -> Unit
) {
    val selectedChipData: MutableState<ChipData?> = remember {
        mutableStateOf(null)
    }

    Column(modifier = modifier) {
        Text(
            text = "hello there",
            style = MaterialTheme.typography.subtitle1
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = "Programming languages you’re interested in",
            style = MaterialTheme.typography.subtitle1
        )

        LazyRow {
            items(listOfLanguages) {
                Button(onClick = { toggleLanguage(it) }) {
                    Text(color = it.color, text = it.name)
                }
            }
        }

        when (settingState) {
            is SettingUiState.Success -> {
                ChipGroup(
                    chips = settingState.languages,
                    selectedChip = selectedChipData.value,
                    onSelectedChanged = { selectedChip ->
                        selectedChipData.value = selectedChip
                    }
                )
            }

            is SettingUiState.Loading -> {

            }

            is SettingUiState.Error -> {

            }
        }

        Button(onClick = { clearDataStore.invoke() }) {
            Text(text = "clear prefs")
        }

    }
}