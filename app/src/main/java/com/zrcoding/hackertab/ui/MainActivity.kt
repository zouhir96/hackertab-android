package com.zrcoding.hackertab.ui

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
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
            TopAppBar(
                elevation = 0.dp
            ) {
                Toolbar(onRefreshBtnClick = { viewModel.fetchPosts() })
            }
        },
        content = {
            HomeScreen(viewModel)
        }
    )
}

@Composable
fun Toolbar(onRefreshBtnClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(start = 12.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_hackertab),
            contentDescription = "",
            modifier = Modifier.width(180.dp),
            tint = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { onRefreshBtnClick() },
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colors.primaryVariant
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_refresh),
                contentDescription = "refresh button",
                tint = colorResource(R.color.icons_tint)
            )
        }
    }
}


