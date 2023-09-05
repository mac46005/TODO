package com.preciado.todo.core.composables_todo.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.preciado.todo.core.composable_templates.components.ListItemTemplate


@Composable
fun EmptyListMessage(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        LazyColumn(
            modifier = Modifier.align(Alignment.TopStart)
        ){
            items(20){
                ListItemTemplate(onClick = { /*TODO*/ }) {
                    Text(text = it.toString())
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewEmptyListMessage(){
    EmptyListMessage()
}