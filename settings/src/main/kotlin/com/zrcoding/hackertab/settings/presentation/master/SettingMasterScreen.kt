package com.zrcoding.hackertab.settings.presentation.master

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.design.theme.HackertabTheme
import com.zrcoding.hackertab.design.theme.dimension
import com.zrcoding.hackertab.settings.BuildConfig
import com.zrcoding.hackertab.settings.R

@Composable
fun SettingsTopBar(
    onBackClicked: () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = MaterialTheme.dimension.none
    ) {
        Button(
            onClick = onBackClicked,
            modifier = Modifier.size(MaterialTheme.dimension.extraBig),
            shape = MaterialTheme.shapes.large,
            contentPadding = PaddingValues(MaterialTheme.dimension.none),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colors.onBackground
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back),
                contentDescription = "back button",
            )
        }
    }
}

@Preview
@Composable
private fun SettingsTopBarPreview() {
    HackertabTheme {
        SettingsTopBar {}
    }
}

@Composable
fun SettingMasterScreen(
    onNavigateToTopics: () -> Unit,
    onNavigateToSources: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(
                top = MaterialTheme.dimension.extraBig,
                bottom = MaterialTheme.dimension.default
            )
            .padding(horizontal = MaterialTheme.dimension.screenPaddingHorizontal)
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimension.large)
        ) {
            SettingItemsContainer {
                SettingItem(R.string.setting_master_screen_topics, onClick = onNavigateToTopics)
                SettingItem(R.string.setting_master_screen_sources, onClick = onNavigateToSources)
            }
        }
        AppVersionName(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
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
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.subtitle1
            )
            Icon(
                modifier = Modifier.size(MaterialTheme.dimension.big),
                painter = painterResource(id = R.drawable.ic_baseline_arrow_forward),
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground
            )
        }
        Divider(
            color = MaterialTheme.colors.onBackground,
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

@Composable
fun AppVersionName(modifier: Modifier = Modifier) {
    val versionName = BuildConfig.VERSION_NAME
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.setting_master_screen_version_name, versionName),
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.subtitle1
    )
}