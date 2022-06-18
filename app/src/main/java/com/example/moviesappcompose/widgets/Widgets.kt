package com.example.moviesappcompose.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.moviesappcompose.model.Movies
import com.example.moviesappcompose.model.getMovies

@Preview
@Composable
fun MoviesRow(movies: Movies = getMovies()[0], onItemClick: (String) -> Unit = {}){
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth()
        /*.height(130.dp)*/
        .clickable {
            onItemClick(movies.id)
        },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 6.dp){
        Row(horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(15.dp)
                .size(100.dp),
                shape = CircleShape ,
                elevation = 5.dp) {
                AsyncImage(model = movies.images[0], contentDescription ="Movie Image",contentScale = ContentScale.Crop, modifier = Modifier.clip(
                    CircleShape) )
                /*Image(modifier = Modifier.clip(CircleShape), painter = rememberAsyncImagePainter(model = movies.images[0]),
                    contentDescription = "Movie Image")*/
                /*Icon(imageVector = Icons.Default.Movie, contentDescription = "Movies Image")*/
            }
            Column(modifier = Modifier.padding(5.dp)) {
                Text(text = movies.title, modifier = Modifier.padding(top = 15.dp), style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movies.directors}", modifier = Modifier.padding(top = 5.dp), style = MaterialTheme.typography.caption)
                Text(text = "Release: ${movies.year}", modifier = Modifier.padding(top = 5.dp), style = MaterialTheme.typography.caption)
                
                AnimatedVisibility(visible = expanded) {
                    Column(modifier = Modifier.padding(top = 5.dp)) {
                        Divider()
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp, fontWeight = FontWeight.Bold)){
                                append(movies.plot)
                            }
                        })
                        Divider(modifier = Modifier.padding(top = 5.dp))
                        Text(text = "Actors: ${movies.actors}", modifier = Modifier.padding(top = 5.dp), style = MaterialTheme.typography.caption)
                        Text(text = "Genre: ${movies.genre}", modifier = Modifier.padding(top = 5.dp), style = MaterialTheme.typography.caption)
                        Text(text = "Rating: ${movies.rating}", modifier = Modifier.padding(top = 5.dp), style = MaterialTheme.typography.caption)
                    }
                }
                Icon(imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, contentDescription = "Arrow Down", modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        expanded = !expanded
                    }, tint = Color.DarkGray)
            }

        }
    }
}