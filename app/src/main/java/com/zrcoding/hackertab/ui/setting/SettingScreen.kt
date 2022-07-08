package com.zrcoding.hackertab.ui.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.core.ChipData
import com.zrcoding.hackertab.core.ChipGroup
import com.zrcoding.hackertab.ui.theme.HackertabTheme

@SuppressLint("UnrememberedMutableState")
@Composable
fun SettingScreen() {
    val selectedChipData: MutableState<ChipData?> = remember {
        mutableStateOf(null)
    }

    Column(modifier = Modifier) {
        Text(
            text = "hello there",
            style = MaterialTheme.typography.subtitle1
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = "Programming languages you’re interested in",
            style = MaterialTheme.typography.subtitle1
        )

        val chipsList = listOf(
            ChipData("Github", R.drawable.ic_github),
            ChipData("FreeCodeCamp"),
            ChipData("HackerNews"),
            ChipData("DevTo"),
            ChipData("Reddit"),
            ChipData("Product Hunt")
        )

        ChipGroup(
            chips = chipsList,
            selectedChip = selectedChipData.value,
            onSelectedChanged = { selectedChip ->
                selectedChipData.value = selectedChip
            }
        )
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Composable
fun SettingScreenPreview() {
    HackertabTheme {
        SettingScreen()
    }
}