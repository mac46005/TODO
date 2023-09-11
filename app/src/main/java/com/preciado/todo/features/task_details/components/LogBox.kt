package com.preciado.todo.features.task_details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogBox(

){
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight(3f)
        ){

        }
        Row(
            modifier = Modifier.fillMaxHeight(1f)
        ) {
            TextField(
                value = "",
                onValueChange = {

                }
            )
            Button(
                onClick = {

                }
            ) {

            }
        }
    }
}