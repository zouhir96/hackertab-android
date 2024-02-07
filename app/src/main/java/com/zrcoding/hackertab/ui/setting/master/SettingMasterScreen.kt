package com.zrcoding.hackertab.ui.setting.master

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.ui.theme.HackertabTheme

@Composable
fun SettingMasterScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier.size(40.dp),
                    shape = MaterialTheme.shapes.large,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colors.primaryVariant
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_back),
                        contentDescription = "refresh button",
                        tint = MaterialTheme.colors.secondary
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(top = 40.dp)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SettingItemsContainer {
                SettingItem(R.string.setting_master_screen_topics) {}
                SettingItem(R.string.setting_master_screen_sources) {}
            }
            SettingItemsContainer {
                SettingItem(R.string.setting_master_screen_settings) {}
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingMasterScreenPreview() {
    HackertabTheme {
        SettingMasterScreen()
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
                .padding(horizontal = 10.dp, vertical = 16.dp)
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
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
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
                modifier = Modifier.size(18.dp),
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