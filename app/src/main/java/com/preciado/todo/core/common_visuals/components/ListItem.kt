package com.preciado.todo.core.common_visuals.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.estimateAnimationDurationMillis
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListItem(
    onClick: () -> Unit,
    contentAlignment: Alignment = Alignment.TopStart,
    background: Color = Color.Transparent,
    itemContent: @Composable (BoxScope.() -> Unit),
    ){
    val animatable = remember {
        Animatable(initialValue = 0f)
    }
    LaunchedEffect(key1 = animatable){
        animatable.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 700,
                delayMillis = 0,
                easing = LinearOutSlowInEasing
            )
        )
    }
    Box(
        modifier = Modifier
            .alpha(animatable.value)
            .fillMaxWidth()
            .padding(top = 5.dp)
            .clickable {
                onClick()
            }
            .background(background),
        contentAlignment = contentAlignment
    ){
        itemContent(this)
        Divider(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(top = 25.dp))
    }
}

@Preview
@Composable
fun PreviewListItem(){
    ListItem(onClick = {}){
        Text(text = "Item Content")
    }
}