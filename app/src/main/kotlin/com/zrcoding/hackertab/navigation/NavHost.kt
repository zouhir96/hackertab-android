package com.zrcoding.hackertab.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zrcoding.hackertab.design.theme.HackertabTheme
import com.zrcoding.hackertab.home.presentation.HomeRoute

const val TRANSITION_DURATION = 400

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.HOME.route,
    isExpandedScree: Boolean
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Screen.HOME.route,
        ) {
            HomeRoute(
                isExpandedScree = isExpandedScree,
                onNavigateToSettings = { navController.navigate(Screen.SETTINGS.route) }
            )
        }
        composable(
            route = Screen.SETTINGS.route,
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
            SettingNavHost(
                isExpandedScreen = isExpandedScree,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

@Preview
@Composable
fun MainNavHostPreview() {
    HackertabTheme {
        Surface {
            MainNavHost(
                navController = rememberNavController(),
                startDestination = Screen.SETTINGS.route,
                isExpandedScree = false
            )
        }
    }
}