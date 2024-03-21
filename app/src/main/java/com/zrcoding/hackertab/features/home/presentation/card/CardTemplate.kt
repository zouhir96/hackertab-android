package com.zrcoding.hackertab.features.home.presentation.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.core.getTagColor
import com.zrcoding.hackertab.features.home.domain.models.BaseModel
import com.zrcoding.hackertab.features.home.presentation.CardViewState
import com.zrcoding.hackertab.theme.HackertabTheme
import com.zrcoding.hackertab.theme.dimenExtraLarge
import com.zrcoding.shared.core.openUrlInBrowser
import com.zrcoding.shared.domain.models.SourceName


@Composable
fun CardTemplate(
    modifier: Modifier = Modifier,
    cardUiState: CardViewState,
    cardItem: @Composable (SourceName, BaseModel) -> Unit,
) {
    Card(
        elevation = 3.dp,
        modifier = modifier
            .padding(end = 8.dp, top = 16.dp)
            .fillMaxHeight(),
        shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
    ) {
        Column {
            CardHeader(
                title = cardUiState.source.label,
                icon = cardUiState.source.icon
            )
            when (
                val state =
                    cardUiState.state.collectAsState(initial = CardViewState.State.Loading).value
            ) {
                CardViewState.State.Loading -> Loading(stringResource(R.string.loading))

                CardViewState.State.Error -> EmptySource()

                is CardViewState.State.Success -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(bottom = dimenExtraLarge)
                    ) {
                        items(
                            items = state.articles,
                            key = { item ->
                                item.id
                            }
                        ) { item ->
                            cardItem(cardUiState.source.name, item)
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
            primaryInfoSection = {
                TextWithStartIcon(text = "il y a 1h", icon = R.drawable.ic_time_24)
            },
            modifier = Modifier,
            tags = listOf("Java", "Kotlin", "JavaScript", "android development"),
        )
    }
}

@Composable
fun SourceItemTemplate(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = MaterialTheme.colors.onBackground,
    description: String? = null,
    primaryInfoSection: @Composable RowScope.() -> Unit,
    url: String? = null,
    tags: List<String>? = null,
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
            color = titleColor,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2
        )
        Spacer(modifier = modifier.height(8.dp))

        if (description.isNullOrBlank().not()) {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = description.orEmpty(),
                style = MaterialTheme.typography.body2,
                maxLines = 2
            )
            Spacer(modifier = modifier.height(4.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            primaryInfoSection()
        }
        Spacer(modifier = modifier.height(4.dp))

        tags?.let {
            CardItemTags(modifier = Modifier.fillMaxWidth(), tags = it)
        }
    }
}

@Composable
fun CardItemTags(
    modifier: Modifier = Modifier,
    tags: List<String>,
) {
    val isTagsBlank = tags.isEmpty() || (tags.size == 1 && tags.first().isBlank())
    if (isTagsBlank) return
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tags.forEach {
            val color = it.getTagColor()
            TextWithStartIcon(
                text = it,
                icon = R.drawable.ic_ellipse,
                tint = color
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardHeaderPreview() {
    HackertabTheme {
        CardHeader(title = "HackerNews", icon = R.drawable.ic_github)
    }
}

@Composable
fun CardHeader(title: String, icon: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = colorResource(id = R.color.cart_header_background)
            )
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "card header icon",
            modifier = Modifier.size(22.dp)
        )
        Spacer(
            modifier = Modifier.width(12.dp)
        )
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
        CircularProgressIndicator(
            modifier = Modifier.size(25.dp),
            color = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.body1
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
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Gray,
    textStyle: TextStyle = MaterialTheme.typography.caption,
    textDecoration: TextDecoration = TextDecoration.None,
    icon: Int,
    tint: Color = Color.Gray
) {
    Row(
        modifier = modifier.padding(end = 8.dp),
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
            overflow = TextOverflow.Ellipsis,
            textDecoration = textDecoration
        )
    }
}