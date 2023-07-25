package com.example.todoappwithcompose.navigation.destinations

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.todoappwithcompose.ui.screens.splash.SplashScreen
import com.example.todoappwithcompose.utils.Constants

fun NavGraphBuilder.splashComposable(
    navigateToListScreen : () -> Unit
) {
    composable(
        route = Constants.SPLASH_SCREEN,
        exitTransition = {
            slideOutVertically(
                animationSpec = tween(durationMillis = 2000),
                targetOffsetY = { fullHeight ->
                    -fullHeight
                }
            )
        }
    ) {
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}