package com.zrcoding.hackertab.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.zrcoding.hackertab.features.navigation.MainNavHost
import com.zrcoding.hackertab.theme.HackertabTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            window.statusBarColor = Color.BLACK
        }
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            HackertabTheme {
                HackertabApp()
            }
        }
    }
}

@Composable
fun HackertabApp() {
    val navController = rememberNavController()
    MainNavHost(navController = navController)
}


