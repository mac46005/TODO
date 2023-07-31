package com.preciado.todo.core.common_visuals.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

enum class ToastDuration(val duration: kotlin.Long){
    Short(5000),
    Long(10000)
}

@Composable
fun ToastMessage(
    message: String,
    duration: Long = ToastDuration.Short.duration
){
    var visible: Boolean by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(visible){
        delay(duration)
        visible = false
    }
}

@Preview
@Composable
fun PreviewToastMessage(){
    ToastMessage(message = "This is a message from a toast!")
}