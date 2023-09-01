package com.preciado.todo.core.common_visuals.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadBar(
    barHeight: Dp = 30.dp,
    barWidthFraction: Float = 1f,
    barColor: Color = Color.Transparent,
    indicatorColor: Color = Color.Red,
    moveDuration: Int = 3000,
    growthDuration: Int = 3000
){
    val animateMovement = remember {
        Animatable(initialValue = 0f)
    }

    val animateWidth = remember{
        Animatable(initialValue = 0f)
    }

    LaunchedEffect(key1 = animateMovement){
        animateMovement.animateTo(
            1f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = moveDuration
                    0.0f at 0 with LinearOutSlowInEasing
                    1.0f at moveDuration with LinearOutSlowInEasing
                },
                repeatMode = RepeatMode.Restart
            )
        )

    }
    LaunchedEffect(key1 = animateWidth){
        animateWidth.animateTo(
            targetValue = 1f,
            animationSpec = InfiniteRepeatableSpec(
                animation = keyframes {
                    durationMillis = 1000
                    0.0f at 0 with LinearOutSlowInEasing
                    1.0f at 400 with LinearOutSlowInEasing
                    0.0f at 1000 with LinearOutSlowInEasing
                }
            )
        )
    }







    Box(modifier = Modifier
        .height(barHeight)
        .fillMaxSize(barWidthFraction)
        .background(barColor)
    ){


        Box(modifier = Modifier
            .graphicsLayer {


                translationX = animateMovement.value * 1000f
            }
            .background(indicatorColor)
            .width(Dp(animateWidth.value * 200))
            .height(barHeight)
        )
    }
}
@Preview
@Composable
fun PreviewLoadBar(){
    LoadBar()
}