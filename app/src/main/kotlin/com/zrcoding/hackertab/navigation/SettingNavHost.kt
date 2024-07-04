package com.zrcoding.hackertab.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.zrcoding.hackertab.design.theme.HackertabTheme
import com.zrcoding.hackertab.design.theme.dimension
import com.zrcoding.hackertab.settings.R
import com.zrcoding.hackertab.settings.presentation.master.SettingMasterScreen
import com.zrcoding.hackertab.settings.presentation.sources.SettingSourcesRoute
import com.zrcoding.hackertab.settings.presentation.topics.SettingTopicsRoute

@Composable
fun SettingNavHost(
    isExpandedScreen: Boolean,
    onNavigateBack: () -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            SettingsTopBar {
                if (isExpandedScreen) {
                    navController.currentDestination?.route?.let {
                        if (it == SettingScreen.SETTING_TOPICS.route) {
                            onNavigateBack()
                        } else {
                            navController.popBackStack()
                        }
                    }
                } else {
                    navController.currentDestination?.route?.let {
                        if (it == SettingScreen.SETTING_MASTER.route) {
                            onNavigateBack()
                        } else {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    ) {
        if (isExpandedScreen) {
            TwoPanNavHost(
                modifier = Modifier.padding(it),
                navController = navController
            )
        } else {
            OnePanNavHost(
                modifier = Modifier.padding(it),
                navController = navController
            )
        }
    }
}

@Composable
fun SettingsTopBar(
    onBackClicked: () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = MaterialTheme.dimension.none
    ) {
        Button(
            onClick = onBackClicked,
            modifier = Modifier.size(MaterialTheme.dimension.extraBig),
            shape = MaterialTheme.shapes.large,
            contentPadding = PaddingValues(MaterialTheme.dimension.none),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colors.onBackground
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back),
                contentDescription = "back button",
            )
        }
    }
}

@Preview
@Composable
private fun SettingsTopBarPreview() {
    HackertabTheme {
        SettingsTopBar {}
    }
}

@Composable
fun OnePanNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SettingScreen.SETTING_MASTER.route
    ) {
        composable(
            SettingScreen.SETTING_MASTER.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            }
        ) {
            SettingMasterScreen(
                showSelectedItem = false,
                onNavigateToTopics = { navController.navigate(SettingScreen.SETTING_TOPICS.route) },
                onNavigateToSources = { navController.navigate(SettingScreen.SETTING_SOURCES.route) }
            )
        }
        composable(
            route = SettingScreen.SETTING_TOPICS.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(TRANSITION_DURATION)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(TRANSITION_DURATION)
                )
            }
        ) {
            SettingTopicsRoute()
        }
        composable(
            SettingScreen.SETTING_SOURCES.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(TRANSITION_DURATION)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(TRANSITION_DURATION)
                )
            }
        ) {
            SettingSourcesRoute()
        }
    }
}

@Composable
fun TwoPanNavHost(modifier: Modifier = Modifier, navController: NavHostController) {

    fun navigateWithPopUpToTopics(route: String) {
        navController.navigate(
            route = route,
            navOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(SettingScreen.SETTING_TOPICS.route) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // re selecting the same item
                launchSingleTop = true
                // Restore state when re selecting a previously selected item
                restoreState = true
            }
        )
    }

    Row(
        modifier = modifier.fillMaxSize()
    ) {
        SettingMasterScreen(
            modifier = Modifier.width(400.dp),
            showSelectedItem = true,
            onNavigateToTopics = {
                navigateWithPopUpToTopics(SettingScreen.SETTING_TOPICS.route)
            },
            onNavigateToSources = {
                navigateWithPopUpToTopics(SettingScreen.SETTING_SOURCES.route)
            }
        )

        NavHost(
            modifier = modifier
                .width(0.dp)
                .weight(1f),
            navController = navController,
            startDestination = SettingScreen.SETTING_TOPICS.route
        ) {
            composable(
                route = SettingScreen.SETTING_TOPICS.route,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() }
            ) {
                SettingTopicsRoute()
            }
            composable(
                SettingScreen.SETTING_SOURCES.route,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() }
            ) {
                SettingSourcesRoute()
            }
        }
    }
}