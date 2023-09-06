package com.preciado.todo.core.composables.composable_templates.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import com.preciado.todo.ui.theme.TODOTheme

@Composable
fun TransparentButton(
    onClick: (() -> Unit),
    enabled: Boolean = true,
    content: @Composable (RowScope.() -> Unit)
){

    TODOTheme() {
        val interactionSource = remember {
            MutableInteractionSource()
        }
        val isPressed by interactionSource.collectIsPressedAsState()
        val color by animateColorAsState(if(isPressed) Color.Red else Color.Transparent)



        Button(
            onClick = onClick,
            enabled = enabled,
            shape = RectangleShape,
            colors = ButtonDefaults
                .buttonColors(
                    containerColor = color,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
            interactionSource = interactionSource
        ) {
            content(this)
        }
    }
}

@Preview
@Composable
fun PreviewTransparentButton(){
    TransparentButton(onClick = {}){
        Text(text = "Hello World")
    }
}