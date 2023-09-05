package com.preciado.todo.core.composable_templates.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun HeaderTemplate(
    modifier: Modifier = Modifier,
    annotatedString: AnnotatedString,
    contentAlignment: Alignment = Alignment.Center,
){
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = contentAlignment,
    ){
        Text(text = annotatedString)
    }
}
@Preview
@Composable
fun PreviewSubTitleBar(){
    HeaderTemplate(
        annotatedString = buildAnnotatedString {
            pushStyle(SpanStyle(
                fontSize = 50.sp
            ))
            append("Header")
        }
    )
}