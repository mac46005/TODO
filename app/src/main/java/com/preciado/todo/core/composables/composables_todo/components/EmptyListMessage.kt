package com.preciado.todo.core.composables.composables_todo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.preciado.todo.core.composables.composable_templates.components.ListItemTemplate
import com.preciado.todo.ui.theme.TODOTheme


@Composable
fun EmptyListMessage(
    message: String = "",
    action: @Composable (BoxScope.() -> Unit) = {},
){
    TODOTheme() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ){
            Box(
                modifier =
                Modifier
                    .align(Alignment.Center)
                    .background(Color.Gray)
                    .height(300.dp)
                    .width(300.dp)
                    .zIndex(10f)
            ){
                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(30.dp),
                    text = message
                )
                action(this)
            }
            LazyColumn(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .zIndex(1f)
            ){
                items(60){
                    ListItemTemplate(onClick = { /*TODO*/ }) {
                        Divider(modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(top = 13.dp))
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewEmptyListMessage(){
    EmptyListMessage(
        message = "This list is empty.\n Lets add some items to the list",
    )
}