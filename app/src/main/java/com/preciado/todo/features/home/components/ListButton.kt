package com.preciado.todo.features.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ListButton(
    onClick: () -> Unit,
    text: String
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
    ) {
        Column {
            Text(text = text)
            Divider(
                thickness = 5.dp
            )
        }
    }
}

@Preview
@Composable
fun PreviewListButton(){
    ListButton(
        onClick = {},
        text = "My Tasks"
    )
}