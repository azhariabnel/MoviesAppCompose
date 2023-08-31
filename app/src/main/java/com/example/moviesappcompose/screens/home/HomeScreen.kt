package com.example.moviesappcompose.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.moviesappcompose.navigation.MoviesScreens
import com.example.moviesappcompose.widgets.NowPlayingRow
import com.example.moviesappcompose.widgets.MyEventListener
import com.example.moviesappcompose.widgets.PopularRow
import com.example.moviesappcompose.widgets.RecommendationRow


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: MoviesViewModel){
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Gray,
            elevation = 5.dp) {
            Text(text = "24/7 Movies",
                modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                color = MaterialTheme.colors.onBackground, fontSize = 18.sp)
        }
    }) {
        MainContent(navController = navController,viewModel)
    }
}


@Composable
fun MainContent(navController: NavController, viewModel: MoviesViewModel){

    MyEventListener {
        when (it) {
            Lifecycle.Event.ON_START -> {
                viewModel.getNowPlaying()
                viewModel.getPopular()
                viewModel.getRecommendation("300")
            }
            else -> {

            }
        }
    }
    val data = viewModel.nowPlayingUiState.collectAsState()
    val datapopular = viewModel.popularUiState.collectAsState()
    val datarec = viewModel.recommendationUiState.collectAsState()

    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {

        Column(modifier = Modifier.padding(5.dp)) {
            Text(text = "Now Playing",fontSize = 24.sp, fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(20.dp))
            LazyRow(modifier = Modifier.fillMaxWidth(),userScrollEnabled = true){
                items(items = data.value.nowPlaying?.results ?: listOf()){ moviesName ->
                    if (moviesName != null) {
                        NowPlayingRow(movies = moviesName,
                            onItemClick = {
                                val movieId = it
                                viewModel.addDetailData(movieId)
                                navController.navigate(MoviesScreens.DetailScreen.route)
                            })
                    }
                }
            }

        }

        Column(modifier = Modifier.padding(5.dp)) {
            Text(text = "Popular",fontSize = 24.sp, fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(20.dp))
            LazyRow(modifier = Modifier.fillMaxWidth(),userScrollEnabled = true){
                items(items = datapopular.value.popular?.results ?: listOf()){ moviesName ->
                    if (moviesName != null) {
                        PopularRow(movies = moviesName,
                            onItemClick = {
                                val movieId = it
                                viewModel.addDetailData(movieId)
                                navController.navigate(MoviesScreens.DetailScreen.route)
                            })
                    }
                }
            }

        }

        Column(modifier = Modifier.padding(5.dp)) {
            Text(text = "Recommendations",fontSize = 24.sp, fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(20.dp))
            LazyRow(modifier = Modifier.fillMaxWidth(),userScrollEnabled = true){
                items(items = datarec.value.recommendation?.results ?: listOf()){ moviesName ->
                    if (moviesName != null) {
                        RecommendationRow(movies = moviesName,
                            onItemClick = {
                                val movieId = it
                                viewModel.addDetailData(movieId)
                                navController.navigate(MoviesScreens.DetailScreen.route)
                            })
                    }
                }
            }

        }
    }
}

