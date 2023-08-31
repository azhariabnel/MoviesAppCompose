package com.example.moviesappcompose.widgets

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil.compose.AsyncImage
import com.ari.servicedata.model.ResultsItem
import com.ari.servicedata.model.ResultsPopularItem
import com.ari.servicedata.model.ResultsTopRatedItem
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import kotlinx.coroutines.delay

@Composable
fun NowPlayingRow(movies: ResultsItem, onItemClick: (String) -> Unit){
    var expanded by remember {
        mutableStateOf(false)
    }
    var poster by remember {
        mutableStateOf(String())
    }

    var placeholderVisible by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        delay(3000)
        placeholderVisible = false
    }
    poster = "https://image.tmdb.org/t/p/w500/${movies.posterPath}"
    Card(modifier = Modifier
        .padding(15.dp)
        .width(320.dp)
        .clickable {
            onItemClick(movies.id.toString())
        },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 6.dp){
        Row(horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(10.dp)
                .width(150.dp)
                .height(250.dp)
                .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                .placeholder(
                    visible = placeholderVisible,
                    highlight = PlaceholderHighlight.shimmer()
                ),
                elevation = 5.dp) {
                AsyncImage(model = poster, contentDescription ="Movie Image",contentScale = ContentScale.FillBounds )
                /*Image(modifier = Modifier.clip(CircleShape), painter = rememberAsyncImagePainter(dummy = movies.images[0]),
                    contentDescription = "Movie Image")*/
                /*Icon(imageVector = Icons.Default.Movie, contentDescription = "Movies Image")*/
            }
            Column(modifier = Modifier.padding(5.dp)) {
                Text(text = movies.title.toString(), modifier = Modifier
                    .padding(top = 15.dp)
                    .placeholder(
                        visible = placeholderVisible,
                        highlight = PlaceholderHighlight.shimmer()
                    ), style = MaterialTheme.typography.h6)
                
                AnimatedVisibility(visible = expanded) {
                    Column(modifier = Modifier.padding(top = 5.dp)) {
                        Divider()
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp, fontWeight = FontWeight.Bold)){
                                append(movies.overview)
                            }
                        })
                        Divider(modifier = Modifier.padding(top = 5.dp))
                    }
                }
                Icon(imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, contentDescription = "Arrow Down", modifier = Modifier
                    .size(25.dp)
                    .placeholder(
                        visible = placeholderVisible,
                        highlight = PlaceholderHighlight.shimmer()
                    )
                    .clickable {
                        expanded = !expanded
                    }, tint = Color.DarkGray)
            }

        }
    }
}

@Composable
fun PopularRow(movies: ResultsPopularItem, onItemClick: (String) -> Unit){
    var expanded by remember {
        mutableStateOf(false)
    }
    var poster by remember {
        mutableStateOf(String())
    }

    var placeholderVisible by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        delay(3000)
        placeholderVisible = false
    }
    poster = "https://image.tmdb.org/t/p/w500/${movies.posterPath}"
    Log.v("TESTA", poster)
    Card(modifier = Modifier
        .padding(15.dp)
        .width(320.dp)
        .clickable {
            onItemClick(movies.id.toString())
        },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 6.dp){
        Row(horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(10.dp)
                .width(150.dp)
                .height(250.dp)
                .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                .placeholder(
                    visible = placeholderVisible,
                    highlight = PlaceholderHighlight.shimmer()
                ),
                elevation = 5.dp) {
                AsyncImage(model = poster, contentDescription ="Movie Image",contentScale = ContentScale.FillBounds )
                /*Image(modifier = Modifier.clip(CircleShape), painter = rememberAsyncImagePainter(dummy = movies.images[0]),
                    contentDescription = "Movie Image")*/
                /*Icon(imageVector = Icons.Default.Movie, contentDescription = "Movies Image")*/
            }
            Column(modifier = Modifier.padding(5.dp)) {
                Text(text = movies.title.toString(), modifier = Modifier
                    .padding(top = 15.dp)
                    .placeholder(
                        visible = placeholderVisible,
                        highlight = PlaceholderHighlight.shimmer()
                    ), style = MaterialTheme.typography.h6)

                AnimatedVisibility(visible = expanded) {
                    Column(modifier = Modifier.padding(top = 5.dp)) {
                        Divider()
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp, fontWeight = FontWeight.Bold)){
                                append(movies.overview)
                            }
                        })
                        Divider(modifier = Modifier.padding(top = 5.dp))
                    }
                }
                Icon(imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, contentDescription = "Arrow Down", modifier = Modifier
                    .size(25.dp)
                    .placeholder(
                        visible = placeholderVisible,
                        highlight = PlaceholderHighlight.shimmer()
                    )
                    .clickable {
                        expanded = !expanded
                    }, tint = Color.DarkGray)
            }

        }
    }
}

@Composable
fun RecommendationRow(movies: ResultsTopRatedItem, onItemClick: (String) -> Unit){
    var expanded by remember {
        mutableStateOf(false)
    }
    var poster by remember {
        mutableStateOf(String())
    }

    var placeholderVisible by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        delay(3000)
        placeholderVisible = false
    }
    poster = "https://image.tmdb.org/t/p/w500/${movies.posterPath}"
    Log.v("TESTA", poster)
    Card(modifier = Modifier
        .padding(15.dp)
        .width(320.dp)
        .clickable {
            onItemClick(movies.id.toString())
        },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 6.dp){
        Row(horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(10.dp)
                .width(150.dp)
                .height(250.dp)
                .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                .placeholder(
                    visible = placeholderVisible,
                    highlight = PlaceholderHighlight.shimmer()
                ),
                elevation = 5.dp) {
                AsyncImage(model = poster, contentDescription ="Movie Image",contentScale = ContentScale.FillBounds )
                /*Image(modifier = Modifier.clip(CircleShape), painter = rememberAsyncImagePainter(dummy = movies.images[0]),
                    contentDescription = "Movie Image")*/
                /*Icon(imageVector = Icons.Default.Movie, contentDescription = "Movies Image")*/
            }
            Column(modifier = Modifier.padding(5.dp)) {
                Text(text = movies.title.toString(), modifier = Modifier
                    .padding(top = 15.dp)
                    .placeholder(
                        visible = placeholderVisible,
                        highlight = PlaceholderHighlight.shimmer()
                    ), style = MaterialTheme.typography.h6)

                AnimatedVisibility(visible = expanded) {
                    Column(modifier = Modifier.padding(top = 5.dp)) {
                        Divider()
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp, fontWeight = FontWeight.Bold)){
                                append(movies.overview)
                            }
                        })
                        Divider(modifier = Modifier.padding(top = 5.dp))
                    }
                }
                Icon(imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, contentDescription = "Arrow Down", modifier = Modifier
                    .size(25.dp)
                    .placeholder(
                        visible = placeholderVisible,
                        highlight = PlaceholderHighlight.shimmer()
                    )
                    .clickable {
                        expanded = !expanded
                    }, tint = Color.DarkGray)
            }

        }
    }
}

@Preview
@Composable
fun PreviewMoviesRow() {
    NowPlayingRow(movies = ResultsItem(), onItemClick = {})
}

@Composable
fun MyEventListener(OnEvent:(event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(newValue = OnEvent)
    val lifeCycleOwner = rememberUpdatedState(newValue = LocalLifecycleOwner.current)

    DisposableEffect(lifeCycleOwner.value){
        val lifecycle = lifeCycleOwner.value.lifecycle
        val observer = LifecycleEventObserver{ source, event ->
            eventHandler.value(event)
        }

        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}