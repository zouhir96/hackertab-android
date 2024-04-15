package com.zrcoding.hackertab.home.presentation.card

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.design.components.ErrorMsgWithBtn
import com.zrcoding.hackertab.design.components.FullScreenViewWithCenterText
import com.zrcoding.hackertab.design.components.Loading
import com.zrcoding.hackertab.design.components.TextWithStartIcon
import com.zrcoding.hackertab.design.components.getTagColor
import com.zrcoding.hackertab.design.theme.HackertabTheme
import com.zrcoding.hackertab.design.theme.dimension
import com.zrcoding.hackertab.home.R
import com.zrcoding.hackertab.home.domain.models.BaseModel
import com.zrcoding.hackertab.home.presentation.CardViewState
import com.zrcoding.hackertab.settings.domain.models.SourceName
import com.zrcoding.hackertab.settings.presentation.common.icon
import com.zrcoding.shared.core.openUrlInBrowser


@Composable
fun CardTemplate(
    modifier: Modifier = Modifier,
    cardUiState: CardViewState,
    cardItem: @Composable (SourceName, BaseModel) -> Unit,
    onRetryBtnClick: () -> Unit
) {
    Card(
        elevation = MaterialTheme.dimension.small,
        modifier = modifier
            .padding(
                end = MaterialTheme.dimension.medium,
                top = MaterialTheme.dimension.default
            )
            .fillMaxHeight(),
        shape = RoundedCornerShape(
            topStart = MaterialTheme.dimension.large,
            topEnd = MaterialTheme.dimension.large
        )
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

                is CardViewState.State.Success -> {
                    if (state.articles.isEmpty()) {
                        FullScreenViewWithCenterText(R.string.empty_source_msg, cardUiState.source.name.value)
                    } else LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimension.large),
                        contentPadding = PaddingValues(bottom = MaterialTheme.dimension.extraBig)
                    ) {
                        items(
                            items = state.articles,
                            key = { item ->
                                item.id
                            }
                        ) { item ->
                            cardItem(cardUiState.source.name, item)
                            Divider(modifier = Modifier.padding(horizontal = MaterialTheme.dimension.large))
                        }
                    }
                }

                is CardViewState.State.Error -> FullScreenViewWithCenterText(
                    R.string.failed_to_load_source,
                    cardUiState.source.name.value
                )

                CardViewState.State.VerifyConnectionAndRefresh -> ErrorMsgWithBtn(
                    text = R.string.no_internet_connect,
                    btnText = R.string.common_retry,
                    onBtnClicked = onRetryBtnClick
                )
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
            .padding(
                horizontal = MaterialTheme.dimension.default,
                vertical = MaterialTheme.dimension.medium
            ),
    ) {

        Text(
            text = title,
            color = titleColor,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2
        )
        Spacer(modifier = modifier.height(MaterialTheme.dimension.medium))

        if (description.isNullOrBlank().not()) {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = description.orEmpty(),
                style = MaterialTheme.typography.body2,
                maxLines = 2
            )
            Spacer(modifier = modifier.height(MaterialTheme.dimension.small))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimension.medium)
        ) {
            primaryInfoSection()
        }
        Spacer(modifier = modifier.height(MaterialTheme.dimension.small))

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
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimension.medium)
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
            modifier = Modifier.size(MaterialTheme.dimension.bigger)
        )
        Spacer(
            modifier = Modifier.width(MaterialTheme.dimension.large)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
        )
    }
}

