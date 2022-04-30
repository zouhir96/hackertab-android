package com.zrcoding.hackertab.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition(
            SplashScreen.KeepOnScreenCondition {
                return@KeepOnScreenCondition viewModel.hackerNewsUiState.dataToDisplay.isEmpty()
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
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(backgroundColor = MaterialTheme.colors.background) {
                    Toolbar()
                }
            },
            content = {
                HomeScreen(viewModel)
            }
        )
    }
}

@Composable
fun Toolbar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_hackertab),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .background(
                    MaterialTheme.colors.background,
                    RoundedCornerShape(50)
                )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = "",
                modifier = Modifier
                    .size(38.dp)
            )
        }
    }
}


