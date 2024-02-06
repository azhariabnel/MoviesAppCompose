package com.example.moviesappcompose.widgets

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        delay(1000)
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
        delay(1000)
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
        delay(1000)
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
fun InfiniteScrollList(
    itemCount: Int,
    loadMoreItems: () -> Unit,
    content: @Composable (Int) -> Unit
) {
    val listState = rememberLazyGridState()

    LazyVerticalGrid(state = listState,
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        )) {
        items(itemCount) { index ->
            content(index)
            if (index == itemCount - 1) {
                loadMoreItems()
            }
        }
    }
}

@Preview
@Composable
fun PreviewMoviesRow() {
    Column(modifier = Modifier
        .height(250.dp)
        .width(300.dp)
        .background(
            color = Color.Gray,
            shape = RoundedCornerShape(corner = CornerSize(10.dp))
        )){
        Text(text = "Chris Sawin",fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center, color = Color.White, modifier = Modifier
                .padding(15.dp)
        )
        Text(text = "The film features some hard-hitting and explosive action sequences that will rightfully cater to fans of the genre. The battle in the basement of the apartment building, where we see Nam-san use a shotgun to blast his way through some of the doctor’s ‘enhanced’ individuals, is a total exhilarating blast. Ma Dong-seok has been a powerhouse for most of his career post Train to Busan, but he sends people flying whenever he throws his fist or pulls the trigger. _Badland Hunters_ also has to break a record for most decapitations in a film.\\r\\n\\r\\n**Full review:** https://bit.ly/bdlndhntr",fontSize = 12.sp, fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Justify, color = Color.White, modifier = Modifier
                .padding(15.dp)
        )
    }
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