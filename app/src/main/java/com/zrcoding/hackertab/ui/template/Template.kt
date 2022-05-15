package com.zrcoding.hackertab.ui.template

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.assets.getTagColor
import com.zrcoding.hackertab.core.CardUiState
import com.zrcoding.hackertab.core.UiText
import com.zrcoding.hackertab.core.openUrlInBrowser
import com.zrcoding.hackertab.ui.theme.HackertabTheme


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
                    Loading(stringResource(R.string.loading))
                }
                cardUiState.uiText != null -> {
                    EmptySource(
                        when (cardUiState.uiText) {
                            is UiText.Message -> cardUiState.uiText.message
                            is UiText.Code -> stringResource(id = cardUiState.uiText.code)
                        }
                    )
                }
                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        itemsIndexed(cardUiState.dataToDisplay) { index, item ->
                            cardItem(item)
                            if (index < cardUiState.dataToDisplay.lastIndex) {
                                Divider(modifier = Modifier.padding(horizontal = 10.dp))
                            }
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
    title: String,
    description: String? = null,
    date: String? = null,
    url: String? = null,
    tags: List<String>? = null,
    informationSection: @Composable () -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .clickable {
                url?.let {
                    openUrlInBrowser(context = context, url = it)
                }
            }
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2
        )
        Spacer(modifier = modifier.height(8.dp))

        description?.let {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = it,
                style = MaterialTheme.typography.body2,
                maxLines = 2
            )
            Spacer(modifier = modifier.height(4.dp))
        }

        date?.let {
            TextWithStartIcon(
                icon = R.drawable.ic_time_24,
                text = date
            )
            Spacer(modifier = modifier.height(8.dp))
        }

        informationSection()

        tags?.let {

            val isTagsBlank = tags.size == 1 && tags.first().isEmpty()
            if (isTagsBlank) return@Column

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                it.onEach {
                    val color = it.getTagColor()
                    TextWithStartIcon(
                        text = it,
                        icon = R.drawable.ic_ellipse,
                        tint = color
                    )
                }
            }
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
            .background(
                color = colorResource(id = R.color.cart_header_background)
            )
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "card header icon",
            modifier = Modifier.padding(start = 24.dp)
        )
        Spacer(modifier = Modifier.width(18.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    HackertabTheme {
        Loading(stringResource(R.string.loading))
    }
}

@Composable
fun Loading(title: String = stringResource(R.string.loading)) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.width(8.dp))
        CircularProgressIndicator(
            modifier = Modifier.size(40.dp),
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun EmptySource(title: String = stringResource(R.string.empty)) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.undraw_connection),
            "",
            modifier = Modifier
                .width(150.dp)
                .height(200.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TextWithStartIcon(
    text: String,
    textColor: Color = Color.Gray,
    textStyle: TextStyle = MaterialTheme.typography.caption,
    icon: Int,
    tint: Color = Color.Gray
) {
    Row(
        modifier = Modifier.padding(end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val iconSize = if (icon == R.drawable.ic_ellipse) 8.dp else 14.dp
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            tint = tint,
            modifier = Modifier.size(iconSize)
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