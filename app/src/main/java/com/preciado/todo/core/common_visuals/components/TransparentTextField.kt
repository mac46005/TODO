package com.preciado.todo.core.common_visuals.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: ((String) -> Unit),
    placeHolder: @Composable (() -> Unit)?
) {
    TextField(value = "Hello", onValueChange = onValueChange, modifier = Modifier.fillMaxWidth())
}

@Preview
@Composable
fun PreviewTransparentTextField() {

}