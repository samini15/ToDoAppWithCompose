package com.example.todoappwithcompose.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoappwithcompose.ui.theme.ToDoAppWithComposeTheme
import com.example.todoappwithcompose.ui.theme.splashScreenBackground
import com.example.todoappwithcompose.utils.Constants
import kotlinx.coroutines.delay
import kotlin.time.Duration

@Composable
fun SplashScreen(
    navigateToListScreen: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        delay(2000)
        navigateToListScreen()
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.splashScreenBackground),
        contentAlignment = Alignment.Center
    ) {
        // TODO Add a logo
    }
}

@Composable
@Preview
private fun SplashScreenPreview() {
    SplashScreen(navigateToListScreen = {})
}

@Composable
@Preview
private fun SplashScreenPreviewDarkTheme() {
    ToDoAppWithComposeTheme(darkTheme = true) {
        SplashScreen(navigateToListScreen = {})
    }
}