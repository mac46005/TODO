package com.preciado.todo.core.composables.composable_templates.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadBar(
    color: Color = Color.Red
){
    val animatable = remember{
        Animatable(initialValue = 0f)
    }

    LaunchedEffect(key1 = animatable){
        animatable.animateTo(
            targetValue = 0f,
            animationSpec = InfiniteRepeatableSpec(
                animation = keyframes {
                    durationMillis = 1000
                    0f at 0
                    1f at 1000
                }
            )
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier.fillMaxWidth(.5f),
            progress = animatable.value,
            color = color
        )
    }
}
@Preview
@Composable
fun PreviewLoadBar(){
    LoadBar()
}