package com.example.moviesappcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesappcompose.screens.detail.DetailScreen
import com.example.moviesappcompose.screens.home.HomeScreen

@Composable
fun MoviesNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
            startDestination = MoviesScreens.HomeScreen.name){
            composable(MoviesScreens.HomeScreen.name){
                HomeScreen(navController = navController)
            }
            composable(MoviesScreens.DetailScreen.name+"/{movies}",
                        arguments = listOf(navArgument(name = "movies"){type = NavType.StringType})){
                backStackEntry ->
                DetailScreen(navController = navController, backStackEntry.arguments?.getString("movies"))
            }
    }
}