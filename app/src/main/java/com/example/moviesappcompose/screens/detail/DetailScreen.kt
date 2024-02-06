package com.example.moviesappcompose.screens.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviesappcompose.screens.home.MoviesViewModel
import com.example.moviesappcompose.widgets.MyEventListener
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController?, viewModel: MoviesViewModel) {
    val movieId = viewModel.detailMovieData

    MyEventListener {
        when (it) {
            Lifecycle.Event.ON_START -> {
                if (movieId != null) {
                    viewModel.getMovieDetail(movieId)
                    viewModel.getMoviesReview(movieId)
                }
            }
            else -> {

            }
        }
    }

    val detailUiState = viewModel.detailMovieUiState.collectAsState()
    val reviewsUiState = viewModel.reviewsUiState.collectAsState()
    val poster = "https://image.tmdb.org/t/p/w500/${detailUiState.value.detailMovie?.posterPath}"
    val title = detailUiState.value.detailMovie?.title
    val overview = detailUiState.value.detailMovie?.overview
    val genre = detailUiState.value.detailMovie?.genres ?: listOf()
    val listReview = reviewsUiState.value.reviews?.results ?: listOf()

    val scrollState = rememberScrollState()

    var placeholderVisible by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        delay(2000)
        placeholderVisible = false
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
                    Text(text = "Movie Detail", modifier = Modifier.padding(top = 10.dp))

                }
            }
        },
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)) {
            AsyncImage(model = poster, contentDescription = "Poster", modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .placeholder(
                    visible = placeholderVisible,
                    highlight = PlaceholderHighlight.shimmer()
                ), contentScale = ContentScale.FillWidth)

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = title.toString(),fontSize = 24.sp, fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(20.dp))
            LazyRow(modifier = Modifier.fillMaxWidth(),userScrollEnabled = true){
                items(items = genre){ index ->
                    Spacer(modifier = Modifier.width(20.dp))
                    Box(modifier = Modifier
                        .height(50.dp)
                        .background(
                            color = Color.Gray,
                            shape = RoundedCornerShape(corner = CornerSize(10.dp))
                        )){
                        Text(text = index?.name.toString(),fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center, color = Color.White, modifier = Modifier
                                .padding(15.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Overview",fontSize = 24.sp, fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(20.dp))
            Text(text = overview.toString(),fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Justify, color = Color.White, modifier = Modifier.padding(20.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Reviews",fontSize = 24.sp, fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(20.dp))
            LazyRow(modifier = Modifier.fillMaxWidth(),userScrollEnabled = true){
                items(items = listReview){ item ->
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(modifier = Modifier
                        .wrapContentHeight()
                        .width(300.dp)
                        .background(
                            color = Color.Gray,
                            shape = RoundedCornerShape(corner = CornerSize(10.dp))
                        )){
                        Text(text = item?.author.toString(),fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center, color = Color.White, modifier = Modifier
                                .padding(15.dp)
                        )
                        Text(text = item?.content.toString(),fontSize = 12.sp, fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Justify, color = Color.White, maxLines = 5, overflow = TextOverflow.Ellipsis, modifier = Modifier
                                .padding(15.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetail() {
    DetailScreen(navController = null, viewModel = hiltViewModel())
}

/*
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
}*/
