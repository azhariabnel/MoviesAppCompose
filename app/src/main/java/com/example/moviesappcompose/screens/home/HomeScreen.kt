package com.example.moviesappcompose.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviesappcompose.model.Movies
import com.example.moviesappcompose.model.getMovies
import com.example.moviesappcompose.navigation.MoviesScreens
import com.example.moviesappcompose.widgets.MoviesRow


@Composable
fun HomeScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Gray,
            elevation = 5.dp) {
            Text(text = "Movies",
                modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                color = MaterialTheme.colors.onBackground, fontSize = 18.sp)
        }
    },) {
        MainContent(navController = navController)
    }
}


@Composable
fun MainContent(navController: NavController,movieList : List<Movies> = getMovies()){
    Column(modifier = Modifier.padding(5.dp)) {
        LazyColumn{
            items(items = movieList){ moviesName ->
                MoviesRow(movies = moviesName){
                    navController.navigate(route = MoviesScreens.DetailScreen.name+"/${moviesName.id}")
                }
            }
        }
    }
}

