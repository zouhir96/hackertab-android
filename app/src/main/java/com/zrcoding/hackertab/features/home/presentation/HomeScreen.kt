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
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.features.home.presentation.card.CardTemplate
import com.zrcoding.hackertab.features.home.presentation.card.ToCardItem
import com.zrcoding.hackertab.theme.HackertabTheme
import com.zrcoding.hackertab.theme.dimenSmall

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
                elevation = 0.dp
            ) {
                HomeScreenTopAppBar(
                    onRefreshBtnClick = onRefreshBtnClick,
                    onSettingBtnClick = onSettingBtnClick
                )
            }
        },
    ) {
        when (viewState) {
            HomeScreenViewState.Loading -> Unit
            is HomeScreenViewState.Cards -> HomeScreenCardsPager(
                modifier = Modifier.padding(it),
                cardViewStates = viewState.cardViewStates
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HackertabTheme {
        HomeScreen(
            viewState = HomeScreenViewState.Loading,
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
        modifier = Modifier.padding(start = 12.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_hackertab),
            contentDescription = "",
            modifier = Modifier.width(180.dp),
            tint = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { onRefreshBtnClick() },
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
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
        Spacer(modifier = Modifier.width(dimenSmall))
        Button(
            onClick = { onSettingBtnClick() },
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
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
    cardViewStates: List<CardViewState>
) {
    val pagerState = rememberPagerState { cardViewStates.size }
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(start = 16.dp, end = 8.dp)
    ) { page ->
        cardViewStates.getOrNull(page)?.let { state ->
            CardTemplate(
                cardUiState = state,
                cardItem = { sourceName, model ->
                    sourceName.ToCardItem(model = model)
                }
            )
        }
    }
}

