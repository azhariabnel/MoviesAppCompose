package com.example.moviesappcompose.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviesappcompose.navigation.MoviesScreens
import com.example.moviesappcompose.widgets.MyEventListener
import com.example.moviesappcompose.widgets.NowPlayingRow
import com.example.moviesappcompose.widgets.PopularRow
import com.example.moviesappcompose.widgets.RecommendationRow
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import kotlinx.coroutines.delay


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
                viewModel.getMoviesGenre()
            }
            else -> {

            }
        }
    }
    val data = viewModel.nowPlayingUiState.collectAsState()
    val datapopular = viewModel.popularUiState.collectAsState()
    val datarec = viewModel.recommendationUiState.collectAsState()
    val datagenre = viewModel.genreUiState.collectAsState()

    val scrollState = rememberScrollState()

    var placeholderGenreVisible by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        delay(2000)
        placeholderGenreVisible = false
    }

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

        Column(modifier = Modifier.padding(5.dp)) {
            Text(text = "Movies by Genre",fontSize = 24.sp, fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(20.dp))
            LazyRow(modifier = Modifier.fillMaxWidth(),userScrollEnabled = true){
                items(items = datagenre.value.genres?.genres ?: listOf()){ genre ->
                    Spacer(modifier = Modifier.width(20.dp))
                    Box(modifier = Modifier
                        .height(50.dp)
                        .background(
                            color = Color.Gray,
                            shape = RoundedCornerShape(corner = CornerSize(10.dp))
                        )
                        .placeholder(
                            visible = placeholderGenreVisible,
                            highlight = PlaceholderHighlight.shimmer()
                        )
                        .clickable {
                            val genreId = genre?.id
                            val genreName = genre?.name
                            viewModel.addGenreId(genreId.toString(),genreName.toString())
                            navController.navigate(MoviesScreens.MovieListByGenre.route)
                        }){
                        Text(text = genre?.name.toString(),fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center, color = Color.White, modifier = Modifier
                                .padding(15.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun MovieListByGenreScreen(navController: NavController? = null, viewModel: MoviesViewModel? = null) {
    val genreId = viewModel?.genreIdData
    val genreName = viewModel?.genreNameData
    if (genreId != null) {
        viewModel.getMoviesGenre(genreId)
    }
    val moviesListState = viewModel?.moviesByGenreUiState?.collectAsState()

    val list = moviesListState?.value?.movies?.results

    var poster by remember {
        mutableStateOf(String())
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
                                navController?.popBackStack()
                            })
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(text = " $genreName Movies", modifier = Modifier.padding(top = 10.dp))

                }
            }
        },
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                // content padding
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
                content = {
                    items(list?.size ?: 0) { index ->
                        poster = "https://image.tmdb.org/t/p/w500/${list?.get(index)?.posterPath}"
                        Card(
                            backgroundColor = Color.Gray,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .clickable {
                                    val movieId = list?.get(index)?.id
                                    viewModel?.addDetailData(movieId.toString())
                                    navController?.navigate(MoviesScreens.DetailScreen.route)
                                },
                            elevation = 8.dp,

                        ) {
                            AsyncImage(model = poster, contentDescription ="Movie Image",contentScale = ContentScale.Fit )
                        }
                    }
                }
            )
        }
    }


}

