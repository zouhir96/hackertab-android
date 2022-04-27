package com.zrcoding.hackertab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.core.Constants.FAKE_HACKER_NEWS
import com.zrcoding.hackertab.hackernews.HackerNews
import com.zrcoding.hackertab.ui.theme.HackertabTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackertabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HackerNews(viewModel.hackerNews)
                }
            }
        }
    }
}

@Composable
fun HackerNews(news: List<HackerNews>) {
    LazyColumn{
        items(news) {item ->
            HackerNewsItem(new = item)
        }
    }
}

@Composable
fun HackerNewsItem(new: HackerNews) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = new.title, maxLines = 2)
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HackertabTheme {
        HackerNews(
            FAKE_HACKER_NEWS
        )
    }
}