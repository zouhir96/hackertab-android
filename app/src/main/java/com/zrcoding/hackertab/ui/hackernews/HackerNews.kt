package com.zrcoding.hackertab.ui.hackernews

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.core.Constants
import com.zrcoding.hackertab.core.createPostUrlOpeningIntent
import com.zrcoding.hackertab.ui.shared.CardHeader
import com.zrcoding.hackertab.ui.shared.Loading
import com.zrcoding.hackertab.ui.theme.HackertabTheme
import com.zrcoding.hackertab.ui.theme.Typography
import java.text.DateFormat

data class HackerNews(
    val title: String,
    val url: String,
    val time: Long,
    val descendants: Long,
    val score: Long
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HackertabTheme {
        HackerNews(
            Constants.FAKE_HACKER_NEWS
        )
    }
}

@Composable
fun HackerNews(news: List<HackerNews>) {
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
        CardHeader(title = "HackerNews", icon = R.drawable.ic_score)
        if (news.isEmpty()) {
            Loading("Loading ...")
        } else{
            LazyColumn {
                items(news) { item ->
                    HackerNewsItem(new = item)
                    Divider(modifier = Modifier.padding(horizontal = 10.dp))
                }
            }
        }
    }
}

@Composable
fun HackerNewsItem(new: HackerNews) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .clickable {
                startActivity(
                    context,
                    createPostUrlOpeningIntent(new.url),
                    null
                )
            }
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = new.title,
            style = Typography.body1,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            TextWithStartIcon(
                text = stringResource(id = R.string.score, new.score),
                color = Color.Red,
                textStyle = Typography.caption,
                icon = R.drawable.ic_score,
                tint = Color.Red
            )
            TextWithStartIcon(
                text = DateFormat.getDateInstance().format(new.time),
                color = Color.Gray,
                textStyle = Typography.caption,
                icon = R.drawable.ic_time
            )
            TextWithStartIcon(
                text = stringResource(id = R.string.comments, new.descendants),
                textStyle = Typography.caption,
                color = Color.Gray,
                icon = R.drawable.ic_comment
            )
        }
    }
}

@Composable
fun TextWithStartIcon(
    text:String,
    color:Color,
    textStyle: TextStyle,
    icon:Int,
    tint:Color = Color.Gray
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            tint = tint,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = color,
            style = textStyle,
        )
        Spacer(modifier = Modifier.width(24.dp))
    }
}


