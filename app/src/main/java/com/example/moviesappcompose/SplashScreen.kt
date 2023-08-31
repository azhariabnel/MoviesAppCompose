package com.example.moviesappcompose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moviesappcompose.navigation.MoviesNavigation
import com.example.moviesappcompose.navigation.MoviesScreens
import kotlinx.coroutines.delay

@Composable
fun FadeOutSplash(navController: NavHostController) {
    var startFadeIn by remember {
        mutableStateOf(false)
    }
    val alphaFade = animateFloatAsState(
        targetValue = if (startFadeIn) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000
        )
    )

    LaunchedEffect(key1 = true) {
        startFadeIn = true
        delay(2000)
    }
    SScreen(alpha = alphaFade.value)
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(MoviesScreens.HomeScreen.route)
    }
}

@Composable
fun SScreen(alpha: Float) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_movie),
            contentDescription = "Logo Splash",
            modifier = Modifier
                .width(180.dp)
                .height(197.dp)
                .alpha(alpha)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "24/7 \n Movies",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .alpha(alpha)
        )
    }
}
