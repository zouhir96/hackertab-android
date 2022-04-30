package com.zrcoding.hackertab.ui.source.freecodecamp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.ui.shared.CardHeader
import com.zrcoding.hackertab.ui.shared.Loading

data class FreeCodeCamp(
    val title: String,
    val creator: String,
    val link: String,
    val guid: String,
    val isoDate: String,
)

@Composable
fun FreeCodeCampCard(posts: List<FreeCodeCamp>) {
    Column(
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(
                    topStart = 14.dp,
                    topEnd = 14.dp
                )
            )
    ) {
        CardHeader(title = "FreeCodeCamp", icon = R.drawable.ic_score)
        if (posts.isEmpty()) {
            Loading("Loading ...")
        } else {
            LazyColumn {
                items(posts) { item ->
                    Text(text = item.title.trim())
                    Divider(modifier = Modifier.padding(horizontal = 10.dp))
                }
            }
        }
    }
}