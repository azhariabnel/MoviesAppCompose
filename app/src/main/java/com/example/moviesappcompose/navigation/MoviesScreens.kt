package com.example.moviesappcompose.navigation

import java.lang.IllegalArgumentException

sealed class MoviesScreens(val route: String) {
    object SplashScreen : MoviesScreens("splash")
    object HomeScreen : MoviesScreens("home")
    object DetailScreen : MoviesScreens("detail")
}