package com.preciado.todo.core.composable_templates.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: ((String) -> Unit),
    placeHolder: String = "Add placeholder",
    singleLine: Boolean = true,
    maxLines: Int = 1
) {
    TextField(
        value = value,
        onValueChange = onValueChange, modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = placeHolder)},
        colors = TextFieldDefaults
            .outlinedTextFieldColors(
                containerColor = Color.Transparent
            ),
        singleLine = singleLine,
        maxLines = maxLines
    )
}

@Preview
@Composable
fun PreviewTransparentTextField() {
    TransparentTextField(value = "Sample Data", onValueChange = {})
}