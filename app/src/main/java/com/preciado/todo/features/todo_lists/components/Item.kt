package com.preciado.todo.features.todo_lists.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.common_visuals.components.ListItem

@Composable
fun Item(
    name: String,
    onClick: () -> Unit
){
    ListItem(
        onClick = {
            onClick()
        }
    ) {
        Text(text = name)
    }
}

@Preview
@Composable
fun PreviewListItem(){
    Item(name = "List Item"){

    }
}