package com.zrcoding.hackertab.ui.template

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.ui.theme.HackertabTheme

@Composable
fun SourceItemTemplate(
    title: String,
    description: String?,
    date: String,
    tags: List<String>?,
    cardItem: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxHeight(),
    ) {

        Text(
            modifier = modifier.fillMaxWidth(),
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        
        Spacer(modifier = modifier.height(4.dp))
        description?.let {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = title,
                color = Color.LightGray,
                fontSize = 14.sp
            )
        }

        TextWithStartIcon(
            icon = R.drawable.ic_time_24,
            text = date
        )

        cardItem()

        tags?.let {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                it.onEach {
                    TextWithStartIcon(text = it, icon = R.drawable.ic_comment)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SourceItemTemplatePreview() {
    HackertabTheme {
        SourceItemTemplate(
            title = "HackerNews",
            description = "this is a lorem ipsum test",
            date = "il y a 1h",
            modifier = Modifier,
            tags = listOf("Java", "Kotelin"),
            cardItem = {
                TextWithStartIcon(
                    icon = R.drawable.ic_time_24,
                    text = "this is a custom view"
                )
            }
        )
    }
}