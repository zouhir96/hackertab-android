package com.zrcoding.hackertab.ui

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.ui.home.HomeScreen
import com.zrcoding.hackertab.ui.theme.HackertabTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            window.statusBarColor = Color.BLACK
        }
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition(
            SplashScreen.KeepOnScreenCondition {
                return@KeepOnScreenCondition listOf(
                    viewModel.hackerNewsUiState,
                    viewModel.redditUiState,
                    viewModel.freeCodeCampUiState,
                    viewModel.githubUiState,
                ).any {
                    it.value.loading
                }
            }
        )
        super.onCreate(savedInstanceState)
        setContent {
            HackertabTheme {
                Content(viewModel)
            }
        }
    }
}

@Composable
fun Content(viewModel: MainViewModel) {
    // A surface container using the 'background' color from the theme
    Scaffold(
        topBar = {
            TopAppBar {
                Toolbar(
                    onRefreshBtnClick = { viewModel.fetchPosts() },
                    onSettingBtnClick = {}
                )
            }
        },
        content = {
            HomeScreen(viewModel)
        }
    )
}

@Composable
fun Toolbar(
    onRefreshBtnClick: () -> Unit,
    onSettingBtnClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_hackertab),
            contentDescription = "",
            modifier = Modifier.width(200.dp),
            tint = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { onRefreshBtnClick()}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_refresh),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        }
        /*IconButton(
            onClick = { onSettingBtnClick()}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        }*/
    }
}


