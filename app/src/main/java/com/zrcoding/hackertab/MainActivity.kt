package com.zrcoding.hackertab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.zrcoding.hackertab.core.Constants.FAKE_HACKER_NEWS
import com.zrcoding.hackertab.ui.source.hackernews.HackerNewsCard
import com.zrcoding.hackertab.ui.source.freecodecamp.FreeCodeCampCard
import com.zrcoding.hackertab.ui.theme.HackertabTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition(
            SplashScreen.KeepOnScreenCondition {
                return@KeepOnScreenCondition viewModel.hackerNews.isEmpty()
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
                TopAppBar(backgroundColor = Color.White) {
                    Toolbar()
                }
            },
            content = {
                RedditCard(viewModel.redditUiState)
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
        Icon(painter = painterResource(id = R.drawable.ic_hackertab), contentDescription = "")
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HackertabTheme {
        HackerNewsCard(
            FAKE_HACKER_NEWS
        )
    }
}