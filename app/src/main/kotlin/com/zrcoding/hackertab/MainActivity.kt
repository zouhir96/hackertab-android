package com.zrcoding.hackertab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.zrcoding.hackertab.design.theme.HackertabTheme
import com.zrcoding.hackertab.navigation.MainNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
            HackertabApp(widthSizeClass)
        }
    }
}

@Composable
fun HackertabApp(widthSizeClass: WindowWidthSizeClass) {
    HackertabTheme {
        val navController = rememberNavController()
        Surface {
            MainNavHost(
                navController = navController,
                isExpandedScree = widthSizeClass == WindowWidthSizeClass.Expanded
            )
        }
    }
}


