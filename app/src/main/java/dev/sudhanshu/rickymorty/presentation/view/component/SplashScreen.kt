package dev.sudhanshu.rickymorty.presentation.view.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.sudhanshu.rickymorty.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToMain: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val alphaValue by animateFloatAsState(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2000, easing = LinearEasing), label = ""
        )

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(150.dp)
                .alpha(alphaValue)
                .clip(CircleShape)

        )
    }

    LaunchedEffect(Unit) {
        delay(2000)
        navigateToMain()
    }
}
