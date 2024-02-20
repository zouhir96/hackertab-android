package com.zrcoding.hackertab.features.setting.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.features.navigation.TRANSITION_DURATION
import com.zrcoding.hackertab.features.setting.master.SettingMasterScreen
import com.zrcoding.hackertab.features.setting.sources.SettingSourcesRoute
import com.zrcoding.hackertab.features.setting.topics.SettingTopicsRoute

@Composable
fun SettingNavHost(
    onNavigateBack: () -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            ) {
                Button(
                    onClick = {
                        navController.currentDestination?.route?.let {
                            if (it == SettingScreen.SETTING_MASTER.route) {
                                onNavigateBack()
                            } else {
                                navController.popBackStack()
                            }
                        }
                    },
                    modifier = Modifier.size(40.dp),
                    shape = MaterialTheme.shapes.large,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colors.primaryVariant
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_back),
                        contentDescription = "refresh button",
                        tint = MaterialTheme.colors.secondary
                    )
                }
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
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
                SettingTopicsRoute(onNavigateBack = { navController.popBackStack() })
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
                SettingSourcesRoute(onNavigateBack = { navController.popBackStack() })
            }
        }
    }
}