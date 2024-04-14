package com.zrcoding.hackertab.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zrcoding.hackertab.settings.presentation.master.SettingMasterScreen
import com.zrcoding.hackertab.settings.presentation.master.SettingsTopBar
import com.zrcoding.hackertab.settings.presentation.sources.SettingSourcesRoute
import com.zrcoding.hackertab.settings.presentation.topics.SettingTopicsRoute

@Composable
fun SettingNavHost(
    onNavigateBack: () -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            SettingsTopBar {
                navController.currentDestination?.route?.let {
                    if (it == SettingScreen.SETTING_MASTER.route) {
                        onNavigateBack()
                    } else {
                        navController.popBackStack()
                    }
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
}