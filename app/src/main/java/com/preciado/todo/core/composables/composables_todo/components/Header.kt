package com.preciado.todo.core.composables.composables_todo.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.preciado.todo.core.composables.composable_templates.components.HeaderTemplate

@Composable
fun Header(
    header: String
){
    HeaderTemplate(annotatedString = buildAnnotatedString {
        pushStyle(
            SpanStyle(
                fontSize = 30.sp
            )
        )
        append(header)
        pop()
    })
}

@Preview
@Composable
fun PreviewHeader(){
    Header(header = "Header")
}