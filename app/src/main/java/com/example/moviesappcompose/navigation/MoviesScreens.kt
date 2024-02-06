package com.example.moviesappcompose.navigation

sealed class MoviesScreens(val route: String) {
    object SplashScreen : MoviesScreens("splash")
    object HomeScreen : MoviesScreens("home")
    object DetailScreen : MoviesScreens("detail")
    object MovieListByGenre : MoviesScreens("moviesByGenre")
}