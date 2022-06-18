package com.example.moviesappcompose.navigation

import java.lang.IllegalArgumentException

enum class MoviesScreens {
    HomeScreen,
    DetailScreen;
    companion object {
        fun fromRoute(route: String?): MoviesScreens
            = when (route?.substringBefore("/")){
                HomeScreen.name -> HomeScreen
                DetailScreen.name -> DetailScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognize")
            }
    }
}