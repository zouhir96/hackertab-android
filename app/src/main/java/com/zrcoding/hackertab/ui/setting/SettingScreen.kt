package com.zrcoding.hackertab.ui.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.ui.theme.HackertabTheme

@SuppressLint("UnrememberedMutableState")
@Composable
fun SettingScreen() {


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
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Composable
fun SettingScreenPreview() {
    HackertabTheme {
        SettingScreen()
    }
}