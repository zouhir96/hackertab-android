package com.zrcoding.hackertab.ui.setting.common

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.ui.theme.HackertabTheme
import com.zrcoding.hackertab.ui.theme.dimenBig
import com.zrcoding.hackertab.ui.theme.dimenExtraLarge
import com.zrcoding.hackertab.ui.theme.screenPaddingHorizontal

@Composable
fun SettingScreen(
    @StringRes title: Int,
    @StringRes description: Int,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = dimenExtraLarge)
            .padding(horizontal = screenPaddingHorizontal)
    ) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = stringResource(id = description),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(dimenBig))
        content()
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SettingScreenPreview() {
    HackertabTheme {
        SettingScreen(
            title = R.string.setting_topics_screen_title,
            description = R.string.setting_topics_screen_description
        ) {
            Text(text = "some content here :)")
        }
    }
}