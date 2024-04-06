package com.zrcoding.hackertab.features.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.features.home.presentation.card.CardTemplate
import com.zrcoding.hackertab.features.home.presentation.card.ErrorMsgWithBtn
import com.zrcoding.hackertab.features.home.presentation.card.Loading
import com.zrcoding.hackertab.features.home.presentation.card.ToCardItem
import com.zrcoding.hackertab.theme.HackertabTheme
import com.zrcoding.hackertab.theme.dimension

@Composable
fun HomeRoute(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onNavigateToSettings: () -> Unit
) {
    val viewState = viewModel.viewState.collectAsStateWithLifecycle().value
    HomeScreen(
        modifier = Modifier,
        viewState = viewState,
        onRefreshBtnClick = viewModel::loadData,
        onSettingBtnClick = onNavigateToSettings
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewState: HomeScreenViewState,
    onRefreshBtnClick: () -> Unit,
    onSettingBtnClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                elevation = MaterialTheme.dimension.none
            ) {
                HomeScreenTopAppBar(
                    onRefreshBtnClick = onRefreshBtnClick,
                    onSettingBtnClick = onSettingBtnClick
                )
            }
        },
    ) {
        when (viewState) {
            HomeScreenViewState.Loading -> Loading()
            is HomeScreenViewState.Cards -> if (viewState.cardViewStates.isEmpty()) {
                ErrorMsgWithBtn(
                    text = R.string.no_source_selected,
                    btnText = R.string.common_settings,
                    onBtnClicked = onSettingBtnClick
                )
            } else {
                HomeScreenCardsPager(
                    modifier = Modifier.padding(it),
                    cardViewStates = viewState.cardViewStates,
                    onRefreshBtnClick = onRefreshBtnClick
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenLoadingPreview() {
    HackertabTheme {
        HomeScreen(
            viewState = HomeScreenViewState.Loading,
            onRefreshBtnClick = {},
            onSettingBtnClick = {}
        )
    }
}

@Preview
@Composable
fun HomeScreenEmptyPreview() {
    HackertabTheme {
        HomeScreen(
            viewState = HomeScreenViewState.Cards(emptyList()),
            onRefreshBtnClick = {},
            onSettingBtnClick = {}
        )
    }
}

@Composable
fun HomeScreenTopAppBar(
    onRefreshBtnClick: () -> Unit,
    onSettingBtnClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(start = MaterialTheme.dimension.large),
    ) {
        Text(
            text = stringResource(id = R.string.app_title),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { onRefreshBtnClick() },
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(MaterialTheme.dimension.none),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colors.primaryVariant
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_refresh),
                contentDescription = "refresh button",
                tint = colorResource(R.color.icons_tint)
            )
        }
        Spacer(modifier = Modifier.width(MaterialTheme.dimension.small))
        Button(
            onClick = { onSettingBtnClick() },
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(MaterialTheme.dimension.none),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colors.primaryVariant
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = "settings button",
                tint = colorResource(R.color.icons_tint)
            )
        }
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun HomeScreenTopAppBarPreview() {
    HackertabTheme {
        HomeScreenTopAppBar({}, {})
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenCardsPager(
    modifier: Modifier = Modifier,
    cardViewStates: List<CardViewState>,
    onRefreshBtnClick: () -> Unit
) {
    val pagerState = rememberPagerState { cardViewStates.size }
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(start = MaterialTheme.dimension.default, end = MaterialTheme.dimension.medium)
    ) { page ->
        cardViewStates.getOrNull(page)?.let { state ->
            CardTemplate(
                cardUiState = state,
                cardItem = { sourceName, model ->
                    sourceName.ToCardItem(model = model)
                },
                onRetryBtnClick = onRefreshBtnClick
            )
        }
    }
}

