package com.zrcoding.hackertab.ui.source.freecodecamp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.core.CardUiState
import com.zrcoding.hackertab.ui.shared.CardHeader
import com.zrcoding.hackertab.ui.shared.Loading
import com.zrcoding.hackertab.ui.shared.PostTitle

data class FreeCodeCamp(
    val title: String,
    val creator: String,
    val link: String,
    val guid: String,
    val isoDate: String,
)

@Composable
fun FreeCodeCampCard(freeCodeCampState: CardUiState<List<FreeCodeCamp>>) {
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
        if (freeCodeCampState.dataToDisplay.isEmpty()) {
            Loading("Loading ...")
        } else {
            LazyColumn {
                items(freeCodeCampState.dataToDisplay) { item ->
                    FreeCodeCampItem(item)
                    Divider(modifier = Modifier.padding(horizontal = 10.dp))
                }
            }
        }
    }
}

@Composable
fun FreeCodeCampItem(post:FreeCodeCamp) {
    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        PostTitle(title = post.title.trim())
    }
}