package com.example.moviesappcompose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesappcompose.FadeOutSplash
import com.example.moviesappcompose.screens.detail.DetailScreen
import com.example.moviesappcompose.screens.home.HomeScreen
import com.example.moviesappcompose.screens.home.MoviesViewModel

@Composable
fun MoviesNavigation(){
    val navController = rememberNavController()
    val viewModel = hiltViewModel<MoviesViewModel>()
    NavHost(navController = navController,
            startDestination = MoviesScreens.SplashScreen.route){
            composable(MoviesScreens.SplashScreen.route){
            FadeOutSplash(navController = navController)
             }
            composable(MoviesScreens.HomeScreen.route){
                HomeScreen(navController = navController,viewModel)
            }
            composable(MoviesScreens.DetailScreen.route){
                DetailScreen(navController = navController,viewModel)
            }
            /*composable(MoviesScreens.DetailScreen.name+"/{movies}",
                        arguments = listOf(navArgument(name = "movies"){type = NavType.StringType})){
                backStackEntry ->
                DetailScreen(navController = navController, backStackEntry.arguments?.getString("movies"))
            }*/
    }
}