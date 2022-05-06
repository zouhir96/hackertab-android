package com.zrcoding.hackertab.ui.template

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.assets.getTagColor
import com.zrcoding.hackertab.core.CardUiState
import com.zrcoding.hackertab.ui.theme.HackertabTheme
import com.zrcoding.hackertab.ui.theme.Typography


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> CardTemplate(
    headerIcon: Int,
    headerTitle: String,
    cardUiState: CardUiState<List<T>>,
    cardItem: @Composable (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = 3.dp,
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
    ) {
        Column {
            CardHeader(title = headerTitle, icon = headerIcon)
            when {
                cardUiState.loading -> {
                    Loading("Loading ...")
                }
                cardUiState.uiText != null -> {

                }
                cardUiState.dataToDisplay.isEmpty() -> {

                }
                else -> {
                    LazyColumn {
                        items(cardUiState.dataToDisplay) { item ->
                            cardItem(item)
                            Divider(modifier = Modifier.padding(horizontal = 10.dp))
                        }
                    }
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
            tags = listOf("Java", "Kotlin", "JavaScript", "android development"),
            informationSection = {
                TextWithStartIcon(
                    icon = R.drawable.ic_time_24,
                    text = "this is a custom view"
                )
            }
        )
    }
}

@Composable
fun SourceItemTemplate(
    modifier: Modifier = Modifier,
    date: String? = null,
    tags: List<String>? = null,
    title: String,
    description: String?,
    informationSection: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxHeight().padding(horizontal = 16.dp),
    ) {

        PostTitle(
            title = title
        )
        Spacer(modifier = modifier.height(4.dp))

        description?.let {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = title,
                color = Color.LightGray,
                fontSize = 14.sp
            )
            Spacer(modifier = modifier.height(4.dp))
        }

        date?.let {
            TextWithStartIcon(
                icon = R.drawable.ic_time_24,
                text = date
            )
            Spacer(modifier = modifier.height(4.dp))
        }

        informationSection()

        tags?.let {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                it.onEach {
                    val color = it.getTagColor()
                    TextWithStartIcon(text = it, icon = R.drawable.ic_comment, tint = color)
                }
            }
            Spacer(modifier = modifier.height(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardHeaderPreview() {
    HackertabTheme {
        CardHeader(title = "HackerNews", icon = R.drawable.ic_score)
    }
}

@Composable
fun CardHeader(title: String, icon: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "card header icon",
            modifier = Modifier.padding(start = 24.dp)
        )
        Spacer(modifier = Modifier.width(18.dp))
        Text(
            text = title,
            style = Typography.subtitle1,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    HackertabTheme {
        Loading("Loading")
    }
}

@Composable
fun Loading(title: String = "Loading...") {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = title, color = Color.Black)
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
        )
    }
}

@Composable
fun PostTitle(title: String) {
    Text(
        text = title,
        style = Typography.subtitle1,
        maxLines = 2
    )
}

@Composable
fun TextWithStartIcon(
    text: String,
    textColor: Color = Color.Gray,
    textStyle: TextStyle = Typography.caption,
    icon: Int,
    tint: Color = Color.Gray
) {
    Row(
        modifier = Modifier.padding(end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            tint = tint,
            modifier = Modifier.size(14.dp)
        )
        Text(
            text = text,
            color = textColor,
            style = textStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}