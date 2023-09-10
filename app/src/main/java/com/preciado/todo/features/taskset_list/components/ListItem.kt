package com.preciado.todo.features.taskset_list.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.preciado.todo.core.composables.composable_templates.components.ListItemTemplate

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