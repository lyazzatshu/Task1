package com.example.task1

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "animation") {
        composable(route = "animation") { AnimationScreen(navController) }
        composable(route = "lottie") { LottieScreen(navController) }
    }
}
