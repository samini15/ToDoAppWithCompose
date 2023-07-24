package com.example.todoappwithcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Purple500 = Color(0xFF6200EE)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val LightGray = Color(0xFFFCFCFC)
val LightMediumGray = Color(0x80D1D1D1)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)
val Darker = Color(0xFF151B20)

val LowPriorityColor = Color(0xFF00C980)
val MediumPriorityColor = Color(0xFFFFC114)
val HighPriorityColor = Color(0XFFFF4646)
val NonePriorityColor = Color(0xFFFFFFFF)

val ColorScheme.topAppBarContentColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else Color.White

val ColorScheme.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Purple500

val ColorScheme.screenBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else LightMediumGray
val ColorScheme.cardBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Darker else Color.White

val ColorScheme.textColorPrimary: Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else DarkGray