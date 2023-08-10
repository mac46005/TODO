package com.preciado.todo.features.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoListSelectedMessage(
    paddingBottom: Dp = 0.dp
){
    Box(
        modifier = Modifier.fillMaxSize().padding(bottom = 0.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Select a list!",
            fontSize = 40.sp
        )
    }
}