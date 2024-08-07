package com.zrcoding.hackertab.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zrcoding.hackertab.design.components.ErrorMsgWithBtn
import com.zrcoding.hackertab.design.components.Loading
import com.zrcoding.hackertab.design.components.RoundedIconButton
import com.zrcoding.hackertab.design.theme.HackertabTheme
import com.zrcoding.hackertab.design.theme.dimension
import com.zrcoding.hackertab.home.R
import com.zrcoding.hackertab.home.presentation.card.CardTemplate
import com.zrcoding.hackertab.home.presentation.card.ToCardItem
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    isExpandedScree: Boolean,
    onNavigateToSettings: () -> Unit
) {
    val viewState = viewModel.viewState.collectAsStateWithLifecycle().value
    HomeScreen(
        modifier = Modifier,
        isExpandedScree = isExpandedScree,
        viewState = viewState,
        onRefreshBtnClick = viewModel::loadData,
        onSettingBtnClick = onNavigateToSettings
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    isExpandedScree: Boolean,
    viewState: HomeScreenViewState,
    onRefreshBtnClick: () -> Unit,
    onSettingBtnClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
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
                    isExpandedScree = isExpandedScree,
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
            isExpandedScree = false,
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
            isExpandedScree = false,
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
    isExpandedScree: Boolean,
    cardViewStates: List<CardViewState>,
    onRefreshBtnClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { cardViewStates.size }
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            contentPadding = PaddingValues(
                start = MaterialTheme.dimension.default,
                end = MaterialTheme.dimension.medium
            ),
            pageSize = if (isExpandedScree) PageSize.Fixed(400.dp) else PageSize.Fill
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
        RoundedIconButton(
            modifier = Modifier
                .alpha(0.6f)
                .padding(end = MaterialTheme.dimension.default)
                .align(Alignment.CenterStart),
            size = if (isExpandedScree) 80.dp else 48.dp,
            icon = R.drawable.ic_baseline_arrow_back_ios
        ) {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }
        }
        RoundedIconButton(
            modifier = Modifier
                .alpha(0.6f)
                .padding(end = MaterialTheme.dimension.default)
                .align(Alignment.CenterEnd),
            size = if (isExpandedScree) 80.dp else 48.dp,
            icon = R.drawable.ic_baseline_arrow_forward
        ) {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }
}



