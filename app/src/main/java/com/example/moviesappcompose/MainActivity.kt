package com.example.moviesappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesappcompose.navigation.MoviesNavigation
import com.example.moviesappcompose.ui.theme.MoviesAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppComposeTheme {
                // A surface container using the 'background' color from the theme
                MainApp {
                    MoviesNavigation()
                }
            }
        }
    }
}

@Composable
fun MainApp(content: @Composable () -> Unit){
    MoviesAppComposeTheme {
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainApp {
        MoviesNavigation()
    }
}