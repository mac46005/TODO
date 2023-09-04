package com.preciado.todo.features.todo_lists.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.preciado.todo.core.common_visuals.components.ListItemTemplate

@Composable
fun ListItem(
    name: String,
    onClick: () -> Unit
){
    ListItemTemplate(
        onClick = {
            onClick()
        },
        contentAlignment = Alignment.TopCenter
    ) {
        Text(text = name, fontSize = 20.sp)
    }
}

@Preview
@Composable
fun PreviewListItem(){
    ListItem(name = "List Item"){

    }
}