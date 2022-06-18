package com.example.moviesappcompose.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviesappcompose.model.Movies
import com.example.moviesappcompose.model.getMovies
import com.example.moviesappcompose.widgets.MoviesRow


@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    val movieData = getMovies().filter { movies ->
        movies.id == movieId
    }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Gray,
                elevation = 5.dp
            ) {
                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back",
                        modifier = Modifier
                            .padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
                            .clickable {
                                navController.popBackStack()
                            })
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(text = "Movie Detail", modifier = Modifier.padding(top = 10.dp))

                }
            }
        },
    ) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                MoviesRow(movies = movieData.first())
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Movie Images")
                SetImagesSlider(movieData)
            }
        }
    }

}

@Composable
fun SetImagesSlider(movieData: List<Movies>) {
    LazyRow {
        items(movieData[0].images) { images ->
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .size(250.dp), elevation = 5.dp
            ) {
                AsyncImage(
                    model = images,
                    contentDescription = "Movie Image",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}