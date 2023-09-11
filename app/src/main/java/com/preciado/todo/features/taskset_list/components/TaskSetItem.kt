package com.preciado.todo.features.taskset_list.components

import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.preciado.todo.core.composables.composable_templates.components.ListItemTemplate

@Composable
fun TaskSetItem(
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
        Divider(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Preview
@Composable
fun PreviewListItem(){
    TaskSetItem(name = "List Item"){

    }
}