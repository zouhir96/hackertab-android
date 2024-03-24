package com.zrcoding.hackertab.features.setting.master

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.theme.HackertabTheme
import com.zrcoding.hackertab.theme.dimension

@Composable
fun SettingMasterScreen(
    onNavigateToTopics: () -> Unit,
    onNavigateToSources: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = MaterialTheme.dimension.extraBig)
            .padding(horizontal = MaterialTheme.dimension.big)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimension.large)
    ) {
        SettingItemsContainer {
            SettingItem(R.string.setting_master_screen_topics, onClick = onNavigateToTopics)
            SettingItem(R.string.setting_master_screen_sources, onClick = onNavigateToSources)
        }
        SettingItemsContainer {
            SettingItem(R.string.setting_master_screen_settings) {}
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingMasterScreenPreview() {
    HackertabTheme {
        SettingMasterScreen(onNavigateToTopics = {}, onNavigateToSources = {})
    }
}


@Composable
fun SettingItemsContainer(
    content: @Composable ColumnScope.() -> Unit
) {
    Card(shape = MaterialTheme.shapes.large) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.dimension.large,
                    vertical = MaterialTheme.dimension.default
                )
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingItemsContainerPreview() {
    HackertabTheme {
        SettingItemsContainer {
            SettingItem(R.string.setting_master_screen_topics) {}
            SettingItem(R.string.setting_master_screen_topics) {}
        }
    }
}

@Composable
fun SettingItem(
    @StringRes text: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .clickable(onClick = onClick)
            .padding(MaterialTheme.dimension.large),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimension.large)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = text),
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle1
            )
            Icon(
                modifier = Modifier.size(MaterialTheme.dimension.big),
                painter = painterResource(id = R.drawable.ic_baseline_arrow_forward),
                contentDescription = null,
                tint = MaterialTheme.colors.secondary
            )
        }
        Divider(
            color = MaterialTheme.colors.secondary,
            thickness = 1.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingItemPreview() {
    HackertabTheme {
        SettingItem(R.string.setting_master_screen_topics) {}
    }
}